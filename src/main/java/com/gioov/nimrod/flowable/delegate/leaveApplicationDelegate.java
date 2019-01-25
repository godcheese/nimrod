package com.gioov.nimrod.flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-16
 */
public class leaveApplicationDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(leaveApplicationDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String assignee = (String) delegateExecution.getVariable("assignee");
        boolean approved = (boolean) delegateExecution.getVariable("approved");
        if (approved) {
            LOGGER.info("发送批准通知邮件通知申请人：{}", assignee);
        } else {
            LOGGER.info("发送驳回通知邮件通知申请人：{}", assignee);
        }
    }

}
