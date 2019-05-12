package com.gioov.nimrod.system.api;

import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.System;
import com.gioov.nimrod.system.entity.AttachmentEntity;
import com.gioov.nimrod.system.service.AttachmentService;
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

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = System.Api.ATTACHMENT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttachmentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentRestController.class);

    private static final String ATTACHMENT = "/API/SYSTEM/ATTACHMENT";

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 分页获取所有附件
     *
     * @param page
     * @param rows
     * @return ResponseEntity<Pagination<AttachmentEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<AttachmentEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        Sort sort = new Sort(Sort.Direction.DESC, "gmt_created");
        return new ResponseEntity<>(attachmentService.pageAll(page, rows, sort), HttpStatus.OK);
    }

    /**
     * 更新附件
     *
     * @param file 附件文件
     * @return ResponseEntity<AttachmentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/UPLOAD_ONE')")
    @PostMapping(value = "/upload_one")
    public ResponseEntity<AttachmentEntity> uploadOne(@RequestParam MultipartFile file) throws BaseResponseException {
        return new ResponseEntity<>(attachmentService.insertOne(file), HttpStatus.OK);
    }

    /**
     * 上传附件
     *
     * @param files 附件文件
     * @return ResponseEntity<AttachmentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/UPLOAD_ALL')")
    @PostMapping(value = "/upload_all")
    public ResponseEntity<List<AttachmentEntity>> uploadAll(@RequestParam("file[]") List<MultipartFile> files) throws BaseResponseException {
        return new ResponseEntity<>(attachmentService.insertAll(files), HttpStatus.OK);
    }

    /**
     * 保存附件
     *
     * @param id     附件文件 id
     * @param name   附件文件名
     * @param remark 备注
     * @return ResponseEntity<AttachmentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<AttachmentEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String remark) {
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setId(id);
        attachmentEntity.setName(name);
        attachmentEntity.setRemark(remark);
        AttachmentEntity attachmentEntity1 = attachmentService.updateOne(attachmentEntity);
        return new ResponseEntity<>(attachmentEntity1, HttpStatus.OK);
    }

    /**
     * 指定附件 id list ，批量删除附件
     *
     * @param idList
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(attachmentService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定附件 id ，获取附件信息
     *
     * @param id 附件 id
     * @return ResponseEntity<AttachmentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AttachmentEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(attachmentService.getOne(id), HttpStatus.OK);
    }

    /**
     * 指定附件 id ，获取附件信息
     *
     * @param guid 附件 guid
     * @return ResponseEntity<AttachmentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ATTACHMENT + "/DOWNLOAD')")
    @GetMapping(value = "/download/{guid:.+}")
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String guid) throws BaseResponseException {
        attachmentService.download(httpServletRequest, httpServletResponse, guid);
    }

}
