package com.gioov.nimrod.mail.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.mail.entity.MailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("mailMapper")
@Mapper
public interface MailMapper extends CrudMapper<MailEntity, Long> {

    List<MailEntity> listAllByStatus(@Param("statusList") List<Integer> statusList);

}
