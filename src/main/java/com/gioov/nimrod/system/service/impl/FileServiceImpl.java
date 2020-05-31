package com.gioov.nimrod.system.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.system.entity.FileEntity;
import com.gioov.nimrod.system.mapper.FileMapper;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.system.service.FileService;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.service.UserService;
import com.gioov.tile.util.DataSizeUtil;
import com.gioov.tile.util.FileUtil;
import com.gioov.tile.web.exception.BaseResponseException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private UserService userService;

    @Override
    public Pagination<FileEntity> pageAll(Integer page, Integer rows) {
        Pagination<FileEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<FileEntity> fileEntityPage = fileMapper.pageAll();
        List<FileEntity> fileEntityList = fileEntityPage.getResult();
        List<FileEntity> fileEntityListResult = new ArrayList<>();
        for (FileEntity fileEntity : fileEntityList) {
            if (fileEntity.getUserId() != null) {
                UserEntity userEntity = userService.getOneByIdNoPassword(fileEntity.getUserId());
                if (userEntity != null) {
                    fileEntity.setUsername(userEntity.getUsername());
                }
            }
            fileEntityListResult.add(fileEntity);
        }
        pagination.setRows(fileEntityListResult);
        pagination.setTotal(fileEntityPage.getTotal());
        return pagination;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws BaseResponseException
     * @throws IOException
     */
    private FileEntity upload(MultipartFile file) throws BaseResponseException, IOException {
        FileEntity fileEntity;
        Date date = new Date();
        fileEntity = new FileEntity();
        fileEntity.setUserId(userService.getCurrentUser().getId());
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setSize(file.getSize());
        fileEntity.setPrettySize(DataSizeUtil.pretty(file.getSize()));
        fileEntity.setMimeType(file.getContentType());
        fileEntity.setGmtModified(date);
        fileEntity.setGmtCreated(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String guid = (String.valueOf(calendar.toInstant().toEpochMilli()) + UUID.randomUUID() + "." + FileUtil.getSuffix(file.getOriginalFilename())).replaceAll("-", "");
        String datePath = File.separator + year + File.separator + month;
        String absolutePath = FileUtil.getCurrentRootPath() + dictionaryService.get("FILE", "UPLOAD_PATH") + datePath;
        if (!FileUtil.createDirectory(absolutePath)) {
            throw new BaseResponseException(failureEntity.i18n("file.upload_fail"));
        }
        fileEntity.setGuid(guid);
        fileEntity.setPath(datePath + File.separator + guid);
        String absolutePathGuid = File.separator + FileUtil.filterFileSeparator(absolutePath + File.separator + guid);
        file.transferTo(new File(absolutePathGuid));
        fileMapper.insertOne(fileEntity);
        return fileEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FileEntity uploadOne(MultipartFile file) throws BaseResponseException {
        FileEntity fileEntity;
        try {
            fileEntity = upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException(failureEntity.i18n("file.upload_fail"));
        }
        return fileEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<FileEntity> uploadAll(List<MultipartFile> fileList) throws BaseResponseException {
        if (fileList == null || fileList.isEmpty()) {
            throw new BaseResponseException(failureEntity.i18n("file.upload_fail"));
        }
        List<FileEntity> fileEntityList = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                fileEntityList.add(upload(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException(failureEntity.i18n("file.upload_fail"));
        }
        return fileEntityList.isEmpty() ? null : fileEntityList;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FileEntity saveOne(FileEntity fileEntity) {
        FileEntity fileEntity1 = fileMapper.getOne(fileEntity.getId());
        fileEntity1.setName(fileEntity.getName());
        fileEntity1.setRemark(fileEntity.getRemark());
        fileEntity1.setGmtModified(new Date());
        fileMapper.updateOne(fileEntity1);
        return fileEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        int result = 0;
        for (Long id : idList) {
            FileEntity fileEntity = fileMapper.getOne(id);
            if (fileEntity != null) {
                String uploadPath = (String) dictionaryService.get("ATTACHMENT", "UPLOAD_PATH");
                String filename = File.separator + FileUtil.filterFileSeparator(FileUtil.getCurrentRootPath() + uploadPath + fileEntity.getPath() + fileEntity.getGuid());
                FileUtil.delete(new File(filename));
                fileMapper.deleteOne(id);
            }
            result++;
        }
        return result;
    }

    @Override
    public FileEntity getOne(Long id) {
        FileEntity fileEntity = fileMapper.getOne(id);
        if (fileEntity != null) {
            if (fileEntity.getUserId() != null) {
                UserEntity userEntity = userService.getOneByIdNoPassword(fileEntity.getUserId());
                if (userEntity != null) {
                    fileEntity.setUsername(userEntity.getUsername());
                }
            }
            return fileEntity;
        }
        return null;
    }

    @Override
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String guid) throws BaseResponseException {
        FileEntity fileEntity = fileMapper.getOneByGuid(guid);
        if (fileEntity == null) {
            throw new BaseResponseException(failureEntity.i18n("file.download_fail_file_not_exists"));
        }
        String absolutePath = File.separator + FileUtil.filterFileSeparator(FileUtil.getCurrentRootPath() + dictionaryService.get("FILE", "UPLOAD_PATH") + fileEntity.getPath());
        try {
            FileUtil.download(httpServletRequest, httpServletResponse, fileEntity.getMimeType(), fileEntity.getName(), new File(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException(failureEntity.i18n("file.download_fail"));
        }
    }

    @Override
    public Pagination<FileEntity> pageAllImage(Integer page, Integer rows, Long userId) {
        Pagination<FileEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<FileEntity> fileEntityPage;
        if (userId != null) {
            fileEntityPage = fileMapper.pageAllImageByUserId(userId);
        } else {
            fileEntityPage = fileMapper.pageAllImage();
        }
        List<FileEntity> fileEntityList = fileEntityPage.getResult();
        List<FileEntity> fileEntityListResult = new ArrayList<>();
        for (FileEntity fileEntity : fileEntityList) {
            if (fileEntity.getUserId() != null) {
                UserEntity userEntity = userService.getOneByIdNoPassword(fileEntity.getUserId());
                if (userEntity != null) {
                    fileEntity.setUsername(userEntity.getUsername());
                }
            }
            fileEntityListResult.add(fileEntity);
        }
        pagination.setRows(fileEntityListResult);
        pagination.setTotal(fileEntityPage.getTotal());
        return pagination;
    }

}
