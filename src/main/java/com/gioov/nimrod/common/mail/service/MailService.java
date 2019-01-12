package com.gioov.nimrod.common.mail.service;

import com.gioov.common.mybatis.Sort;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.mail.entity.MailEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface MailService {

    void consume(String message);

    void produce(String message);

    void initialize();

    /**
     * 发送电子邮件
     *
     * @param mailEntity MailEntity
     */
    void send(MailEntity mailEntity);

    /**
     * 分页获取所有邮件队列
     *
     * @param page 页
     * @param rows 每页显示数量
     * @param sort 排序
     * @return Pagination.Result<MailEntity>
     */
    Pagination.Result<MailEntity> pageAll(Integer page, Integer rows, Sort sort);

    /**
     * 新增邮件
     *
     * @param mailEntity MailEntity
     * @return MailEntity
     */
    MailEntity insertOne(MailEntity mailEntity);

    /**
     * 指定队列邮件 id ，批量删除队列邮件
     *
     * @param idList 邮件 id list
     * @return 已删除邮件个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定邮件 id ，获取邮件信息
     *
     * @param id 数据字典 id
     * @return MailEntity
     */
    MailEntity getOne(Long id);

}
