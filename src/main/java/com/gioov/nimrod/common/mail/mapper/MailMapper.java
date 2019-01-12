package com.gioov.nimrod.common.mail.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.common.mail.entity.MailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("mailMapper")
@Mapper
public interface MailMapper extends CrudMapper<MailEntity, Long> {
}
