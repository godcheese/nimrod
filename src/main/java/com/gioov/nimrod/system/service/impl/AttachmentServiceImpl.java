package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.mybatis.Sort;
import com.gioov.common.util.DataSizeUtil;
import com.gioov.common.util.FileUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.properties.AppProperties;
import com.gioov.nimrod.system.entity.AttachmentEntity;
import com.gioov.nimrod.system.mapper.AttachmentMapper;
import com.gioov.nimrod.system.service.AttachmentService;
import com.gioov.nimrod.system.service.DictionaryService;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private AppProperties appProperties;

    @Override
    public Pagination.Result<AttachmentEntity> pageAll(Integer page, Integer rows, Sort sort) {
        List<AttachmentEntity> attachmentEntityList;
        Pagination.Result<AttachmentEntity> paginationResult = new Pagination().new Result<>();
        attachmentEntityList = attachmentMapper.pageAll(new Pageable(page, rows, sort));
        if (attachmentEntityList != null) {
            paginationResult.setRows(attachmentEntityList);
        }
        int count = attachmentMapper.countAll();
        paginationResult.setTotal(count);
        return paginationResult;
    }

    /**
     * 文件上传方法
     *
     * @param file
     * @return
     * @throws BaseResponseException
     * @throws IOException
     */
    private AttachmentEntity upload(MultipartFile file) throws BaseResponseException, IOException {
        AttachmentEntity attachmentEntity;
        Date date = new Date();
        attachmentEntity = new AttachmentEntity();
        attachmentEntity.setName(file.getOriginalFilename());
        attachmentEntity.setSize(file.getSize());
        attachmentEntity.setPrettySize(DataSizeUtil.pretty(file.getSize()));
        attachmentEntity.setMimeType(file.getContentType());
        attachmentEntity.setGmtModified(date);
        attachmentEntity.setGmtCreated(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);

        String datePath = File.separator + year + File.separator + month + File.separator;
//        String uploadPath = FileUtil.getCurrentRootPath() + dictionaryService.get("ATTACHMENT", "UPLOAD_PATH") + datePath;
        String uploadPath = FileUtil.getCurrentRootPath() + appProperties.getAttachmentUploadPath() + datePath;

        if (!FileUtil.createDirectory(uploadPath)) {
            throw new BaseResponseException("附件上传失败");
        }
        attachmentEntity.setPath(datePath);
        String guid = simpleDateFormat.format(date) + "_" + UUID.randomUUID() + "." + FileUtil.getSuffix(file.getOriginalFilename());
        attachmentEntity.setGuid(guid);
        uploadPath = File.separator + FileUtil.filterFileSeparator(uploadPath + guid);
        File outputFile = new File(uploadPath);
        file.transferTo(outputFile);
        attachmentMapper.insertOne(attachmentEntity);
        return attachmentEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AttachmentEntity insertOne(MultipartFile file) throws BaseResponseException {
        AttachmentEntity attachmentEntity;
        try {
            attachmentEntity = upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("附件上传失败");
        }
        return attachmentEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<AttachmentEntity> insertAll(List<MultipartFile> fileList) throws BaseResponseException {
        if (fileList == null || fileList.isEmpty()) {
            throw new BaseResponseException("附件上传失败");
        }
        List<AttachmentEntity> attachmentEntityList = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                attachmentEntityList.add(upload(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("附件上传失败");
        }
        return attachmentEntityList.isEmpty() ? null : attachmentEntityList;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AttachmentEntity updateOne(AttachmentEntity attachmentEntity) {
        AttachmentEntity attachmentEntity1 = attachmentMapper.getOne(attachmentEntity.getId());
        attachmentEntity1.setName(attachmentEntity.getName());
        attachmentEntity1.setRemark(attachmentEntity.getRemark());
        attachmentEntity1.setGmtModified(new Date());
        attachmentMapper.updateOne(attachmentEntity1);
        return attachmentEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        int result = 0;

        for (Long id : idList) {
            AttachmentEntity attachmentEntity = attachmentMapper.getOne(id);

            if (attachmentEntity != null) {
                String filename = File.separator + FileUtil.filterFileSeparator(FileUtil.getCurrentRootPath() + dictionaryService.get("ATTACHMENT", "UPLOAD_PATH") + attachmentEntity.getPath() + attachmentEntity.getGuid());
                FileUtil.delete(new File(filename));
                attachmentMapper.deleteOne(id);
            }
            result++;
        }
        return result;
    }

    @Override
    public AttachmentEntity getOne(Long id) {
        return attachmentMapper.getOne(id);
    }

    @Override
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String guid) throws BaseResponseException {
        AttachmentEntity attachmentEntity = attachmentMapper.getOneByGuid(guid);
        String absolutePath = File.separator + FileUtil.filterFileSeparator(FileUtil.getCurrentRootPath() + dictionaryService.get("ATTACHMENT", "UPLOAD_PATH") + attachmentEntity.getPath() + attachmentEntity.getGuid());
        LOGGER.info("{}", absolutePath);
        File absoluteFile = new File(absolutePath);
        try {
            FileUtil.download(httpServletRequest, httpServletResponse, attachmentEntity.getName(), absoluteFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("文件下载失败");
        }
    }

}
