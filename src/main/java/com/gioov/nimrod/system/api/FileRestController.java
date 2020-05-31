package com.gioov.nimrod.system.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.system.System;
import com.gioov.nimrod.system.entity.FileEntity;
import com.gioov.nimrod.system.service.FileService;
import com.gioov.nimrod.user.service.UserService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = System.Api.FILE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);

    private static final String FILE = "/API/SYSTEM/FILE";

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    /**
     * 分页获取所有文件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination < FileEntity>>
     */
    @OperationLog(value = "分页获取所有文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<FileEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(fileService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 单个文件上传
     *
     * @param file 文件
     * @return ResponseEntity<FileEntity>
     */
    @OperationLog(value = "单个文件上传", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/UPLOAD_ONE')")
    @PostMapping(value = "/upload_one")
    public ResponseEntity<FileEntity> uploadOne(HttpServletRequest httpServletRequest, @RequestParam MultipartFile file) throws BaseResponseException {
        LOGGER.info("User-Agent={}", httpServletRequest.getHeader("User-Agent"));
        return new ResponseEntity<>(fileService.uploadOne(file), HttpStatus.OK);
    }

    /**
     * 多个文件上传
     *
     * @param files 文件 file array
     * @return ResponseEntity<FileEntity>
     */
    @OperationLog(value = "多个文件上传", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/UPLOAD_ALL')")
    @PostMapping(value = "/upload_all")
    public ResponseEntity<List<FileEntity>> uploadAll(@RequestParam("file[]") List<MultipartFile> files) throws BaseResponseException {
        return new ResponseEntity<>(fileService.uploadAll(files), HttpStatus.OK);
    }

    /**
     * 保存文件
     *
     * @param id     文件 id
     * @param name   文件名
     * @param remark 备注
     * @return ResponseEntity<FileEntity>
     */
    @OperationLog(value = "保存文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<FileEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String remark) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(id);
        fileEntity.setName(name);
        fileEntity.setRemark(remark);
        FileEntity fileEntity1 = fileService.saveOne(fileEntity);
        return new ResponseEntity<>(fileEntity1, HttpStatus.OK);
    }

    /**
     * 指定文件 id list，批量删除文件
     *
     * @param idList 文件 id list
     * @return ResponseEntity<Integer> 被删除的数量
     */
    @OperationLog(value = "指定文件 id list，批量删除文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(fileService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定文件 id，获取文件
     *
     * @param id 文件 id
     * @return ResponseEntity<FileEntity>
     */
    @OperationLog(value = "指定文件 id，获取文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<FileEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(fileService.getOne(id), HttpStatus.OK);
    }

    /**
     * 指定文件 guid，下载文件
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param guid                文件 guid
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "指定文件 guid，下载文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/DOWNLOAD')")
    @GetMapping(value = "/download/{guid:.+}")
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String guid) throws BaseResponseException {
        fileService.download(httpServletRequest, httpServletResponse, guid);
    }

    /**
     * 分页获取所有图片文件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination < FileEntity>>
     */
    @OperationLog(value = "分页获取所有图片文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/PAGE_ALL_IMAGE')")
    @GetMapping(value = "/page_all_image")
    public ResponseEntity<Pagination<FileEntity>> pageAllImage(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(fileService.pageAllImage(page, rows, null), HttpStatus.OK);
    }

    /**
     * 分页获取所有图片文件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination < FileEntity>>
     */
    @OperationLog(value = "分页获取所有图片文件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + FILE + "/PAGE_ALL_IMAGE')")
    @GetMapping(value = "/page_all_image_by_current_user")
    public ResponseEntity<Pagination<FileEntity>> pageAllImageByCurrentUser(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(fileService.pageAllImage(page, rows, userService.getCurrentUser().getId()), HttpStatus.OK);
    }
}