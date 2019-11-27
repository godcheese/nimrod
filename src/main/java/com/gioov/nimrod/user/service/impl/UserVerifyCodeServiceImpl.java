package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.user.entity.UserVerifyCodeEntity;
import com.gioov.nimrod.user.mapper.UserVerifyCodeMapper;
import com.gioov.nimrod.user.service.UserVerifyCodeService;
import com.gioov.tile.util.DateUtil;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class UserVerifyCodeServiceImpl implements UserVerifyCodeService {

    @Autowired
    private UserVerifyCodeMapper userVerifyCodeMapper;
    @Autowired
    private FailureEntity failureEntity;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserVerifyCodeEntity addOne(UserVerifyCodeEntity userVerifyCodeEntity) throws BaseResponseException {
//        UserVerifyCodeEntity userVerifyCodeEntity1 = userVerifyCodeMapper.getOneByUserIdAndVerifyFrom(userVerifyCodeEntity.getUserId(), userVerifyCodeEntity.getVerifyFrom());
//        if (userVerifyCodeEntity1 != null) {
//            userVerifyCodeMapper.updateOneByUserIdAndVerifyFrom(userVerifyCodeEntity);
//        } else {
//            userVerifyCodeMapper.insertOne(userVerifyCodeEntity);
//        }
//        return userVerifyCodeEntity;

        UserVerifyCodeEntity userVerifyCodeEntity1 = userVerifyCodeMapper.getOneByUserIdAndVerifyFrom(userVerifyCodeEntity.getUserId(), userVerifyCodeEntity.getVerifyFrom());
        Date gmtCreated = new Date();
        Date gmtExpires = DateUtil.calendarPlus(gmtCreated, Calendar.MINUTE, 10);
        if(userVerifyCodeEntity1 != null) {
            Date gmtCreatedPlus1Minute = DateUtil.calendarPlus(userVerifyCodeEntity1.getGmtCreated(), Calendar.MINUTE, 1);
            if(new Date().getTime() < gmtCreatedPlus1Minute.getTime()) {
                throw new BaseResponseException(failureEntity.i18n("user_verify_code.too_frequent_operation"));
            }
            userVerifyCodeEntity.setGmtExpires(gmtExpires);
            userVerifyCodeEntity.setGmtCreated(gmtCreated);
            userVerifyCodeMapper.updateOneByUserIdAndVerifyFrom(userVerifyCodeEntity);
        } else {
            userVerifyCodeEntity.setGmtExpires(gmtExpires);
            userVerifyCodeEntity.setGmtCreated(gmtCreated);
            userVerifyCodeMapper.insertOne(userVerifyCodeEntity);
        }
//        Integer isOrNotIs = Integer.valueOf(String.valueOf(dictionaryService.get("IS_OR_NOT", "IS")));
//        MailEntity mailEntity = new MailEntity();
//        Map<String, Object> variables = new HashMap<>();
//        String webName = (String) dictionaryService.get("WEB", "NAME");
//        variables.put("webName", webName);
//        variables.put("webUrl", dictionaryService.get("WEB", "URL"));
//        String verifyCode = RandomUtil.randomString( 6, RandomUtil.NUMBER);
//        variables.put("verifyCode", verifyCode);
//        mailEntity.setTo(userEntity.getEmail());
//        mailEntity.setSubject(webName);
//        mailEntity.setText(mailService.loadHtmlTemplate(MailServiceImpl.MAIL_TEMPLATE_ROOT_PATH + "/email_verify_code", variables));
//        mailEntity.setHtml(isOrNotIs);
//        UserVerifyCodeEntity userVerifyCodeEntity1 = new UserVerifyCodeEntity();
//        userVerifyCodeEntity1.setUserId(userEntity.getId());
//        userVerifyCodeEntity1.setVerifyFrom(userEntity.getEmail());
//        userVerifyCodeEntity1.setVerifyCode(verifyCode);
//        userVerifyCodeEntity1.setGmtExpires(gmtExpires);
//        userVerifyCodeEntity1.setGmtCreated(gmtCreated);
//        userVerifyCodeService.addOne(userVerifyCodeEntity);
//        mailService.addOne(mailEntity);

//                UserVerifyCodeEntity userVerifyCodeEntity1 = userVerifyCodeMapper.getOneByUserIdAndVerifyFrom(userVerifyCodeEntity.getUserId(), userVerifyCodeEntity.getVerifyFrom());

        return userVerifyCodeEntity;

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAllByUserIdAndVerifyFrom(Long userId, String verifyFrom) {
//        return userVerifyCodeMapper.deleteAllByUserIdAndRoleIdList(userId, roleIdList);
        return 0;
    }

    /**
     * 判断验证码是否过期，过期返回=true，未过期返回=false
     * @param userVerifyCodeEntity
     * @return
     */
    @Override
    public boolean isExpires(UserVerifyCodeEntity userVerifyCodeEntity) {
        if (new Date().getTime() >= userVerifyCodeEntity.getGmtExpires().getTime()) {
            return true;
        }
        return false;
    }

    @Override
    public UserVerifyCodeEntity getOneByUserIdAndVerifyFrom(Long userId, String verifyFrom, boolean isExpires, String comparisonVerifyCode) throws BaseResponseException {
        UserVerifyCodeEntity userVerifyCodeEntity = userVerifyCodeMapper.getOneByUserIdAndVerifyFrom(userId, verifyFrom);
        if(userVerifyCodeEntity == null) {
            throw new BaseResponseException(failureEntity.i18n("user_verify_code.verification_code_error"));
        }
        if(isExpires(userVerifyCodeEntity) && isExpires) {
            throw new BaseResponseException(failureEntity.i18n("user_verify_code.verification_code_error_or_expires"));
        }
        if(!userVerifyCodeEntity.getVerifyCode().equalsIgnoreCase(comparisonVerifyCode)) {
            throw new BaseResponseException(failureEntity.i18n("user_verify_code.verification_code_error_or_expires"));
        }
        return userVerifyCodeEntity;
    }
}
