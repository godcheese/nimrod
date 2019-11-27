package com.gioov.nimrod.common.operationlog;

import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.nimrod.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-07-17
 */
@Component
public class OperationLogListener {

    @Autowired
    private OperationLogService operationLogService;

    @Async
    @EventListener(OperationLogEvent.class)
    public void handleEvent(OperationLogEvent event) {
        OperationLogEntity operationLogEntity = (OperationLogEntity) event.getSource();
        operationLogService.addOne(operationLogEntity);
    }
}
