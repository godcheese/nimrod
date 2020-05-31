package com.gioov.nimrod.common.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class FailureEntity implements com.gioov.tile.web.http.FailureEntity {

    private static final String NULL = "null";

    private String message;
    private int code;

    @Autowired
    private Common common;

    public FailureEntity i18n(String key, Object... params) {
        String message = String.valueOf(common.i18n(key + ".message", params));
        String code = String.valueOf(common.i18n(key + ".code"));
        int code2 = 0;
        if (code != null && !"".equals(code) && !NULL.equals(code)) {
            code2 = Integer.parseInt(code);
        }
        return new FailureEntity(message, code2);
    }

    public FailureEntity i18n(String key) {
        return i18n(key, new Object() {
        });
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public FailureEntity() {
    }

    public FailureEntity(String message, int code) {
        this.message = message;
        this.code = code;
    }

}