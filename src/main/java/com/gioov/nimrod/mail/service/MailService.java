package com.gioov.nimrod.mail.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.mail.entity.MailEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface MailService {

    /**
     * 初始化电子邮箱配置信息
     */
    void initialize();

    /**
     * 消费队列
     *
     * @param message message
     */
    void consume(String message);

    /**
     * 生产队列
     *
     * @param message message
     */
    void produce(String message);

    /**
     * 分页获取所有邮件队列
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<MailEntity>
     */
    Pagination<MailEntity> pageAll(Integer page, Integer rows);

    /**
     * 新增邮件
     *
     * @param mailEntity MailEntity
     * @return MailEntity
     * @throws BaseResponseException BaseResponseException
     */
    MailEntity addOne(MailEntity mailEntity) throws BaseResponseException;

    /**
     * 指定队列邮件 id，批量删除队列邮件
     *
     * @param idList 邮件 id list
     * @return 已删除邮件个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定电子邮件 id，获取电子邮件
     *
     * @param id 电子邮件 id
     * @return MailEntity
     */
    MailEntity getOne(Long id);

    /**
     * 重试，重发电子邮件
     *
     * @param mailEntityList 电子邮件 list
     */
    void retry(List<MailEntity> mailEntityList);

    /**
     * 将待发送的邮件重新加入到发送队列
     *
     * @param fail 是否将发送失败的邮件也重新加入到队列 默认 false
     */
    void retry(boolean fail);

    String loadHtmlTemplate(String templatePath, Map<String, Object> variables);
}
