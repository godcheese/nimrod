package com.gioov.nimrod.system.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.FileEntity;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface FileService {

    /**
     * 指定文件 id，分页获取所有文件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<FileEntity>
     */
    Pagination<FileEntity> pageAll(Integer page, Integer rows);

    /**
     * 单文件上传
     *
     * @param file MultipartFile
     * @return FileEntity
     * @throws BaseResponseException BaseResponseException
     */
    FileEntity uploadOne(MultipartFile file) throws BaseResponseException;

    /**
     * 多文件上传
     *
     * @param fileList MultipartFile list
     * @return List<FileEntity>
     * @throws BaseResponseException BaseResponseException
     */
    List<FileEntity> uploadAll(List<MultipartFile> fileList) throws BaseResponseException;

    /**
     * 保存文件
     *
     * @param fileEntity FileEntity
     * @return FileEntity
     */
    FileEntity saveOne(FileEntity fileEntity);

    /**
     * 指定文件 id list，批量删除文件
     *
     * @param idList API id list
     * @return int 已删除 API 个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定文件 id，获取文件
     *
     * @param id 文件 id
     * @return FileEntity
     */
    FileEntity getOne(Long id);

    /**
     * 指定 guid，下载文件
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param guid                guid
     * @throws BaseResponseException BaseResponseException
     */
    void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String guid) throws BaseResponseException;

    /**
     * 分页获取所有图片文件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<FileEntity>
     */
    Pagination<FileEntity> pageAllImage(Integer page, Integer rows, Long userId);
}