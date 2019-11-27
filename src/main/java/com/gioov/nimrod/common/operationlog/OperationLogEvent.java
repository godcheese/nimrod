package com.gioov.nimrod.common.operationlog;

import com.gioov.nimrod.system.entity.OperationLogEntity;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-07-17
 */
public class OperationLogEvent extends ApplicationEvent implements Serializable {

    private static final long serialVersionUID = -5144993159534622159L;

    public OperationLogEvent(OperationLogEntity source) {
        super(source);
    }
}
