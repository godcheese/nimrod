package com.gioov.nimrod.common.operationlog;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public enum OperationLogType {

    /**
     * 页面访问
     */
    PAGE(0),

    /**
     * API 调用
     */
    API(1),
    ;

    private int value;

    OperationLogType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

}
