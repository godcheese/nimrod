package com.gioov.nimrod.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-15
 */
public class ManagerTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("经理");
    }
}
