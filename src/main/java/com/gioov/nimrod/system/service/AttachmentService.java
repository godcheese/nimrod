package com.gioov.nimrod.system.service;

import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.AttachmentEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface AttachmentService {

    /**
     * 指定附件 id ，分页获取所有附件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<AttachmentEntity>
     */
    Pagination.Result<AttachmentEntity> pageAll(Integer page, Integer rows, Sort sort);

    /**
     * 新增附件
     *
     * @param file MultipartFile
     * @return AttachmentEntity
     */
    AttachmentEntity insertOne(MultipartFile file) throws BaseResponseException;

    /**
     * 新增附件
     *
     * @param fileList List<MultipartFile>
     * @return List<AttachmentEntity>
     */
    List<AttachmentEntity> insertAll(List<MultipartFile> fileList) throws BaseResponseException;

    /**
     * 保存附件
     *
     * @param attachmentEntity AttachmentEntity
     * @return AttachmentEntity
     */
    AttachmentEntity updateOne(AttachmentEntity attachmentEntity);

    /**
     * 指定附件 id list ，批量删除附件
     *
     * @param idList API id list
     * @return 已删除 API 个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定附件 id ，获取附件信息
     *
     * @param id 附件 id
     * @return AttachmentEntity
     */
    AttachmentEntity getOne(Long id);

    void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String guid) throws BaseResponseException;

}