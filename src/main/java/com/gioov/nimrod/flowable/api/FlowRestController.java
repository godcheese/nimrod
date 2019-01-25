package com.gioov.nimrod.flowable.api;

import com.gioov.example.service.ExampleService;
import com.gioov.nimrod.flowable.Url;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(Url.Api.FLOWABLE + "/flow")
public class FlowRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowRestController.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 发起请假流程
     * @return
     */
    @RequestMapping("/leaveApplication/apply")
    public String apply() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("assignee", "godcheese");
        variables.put("day", 2);
        variables.put("description", "事假");
        runtimeService.startProcessInstanceByKey("leaveApplication", variables);
        return "apply";
    }

    /**
     * 经理组 审批待办列表
     * @return
     */
    @RequestMapping("/leaveApplication/managers/taskList")
    public String managersTaskList() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        if(tasks != null) {
            LOGGER.info("你有{}个待办", tasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());

                LOGGER.info("待办{}：taskId={}，{}, 员工：{}，想要请假{}天", i, tasks.get(i).getId(), tasks.get(i).getName(),processVariables.get("assignee"),  processVariables.get("day"));
            }

        }
        return "taskList";
    }

    /**
     * 审批通过请假申请
     * @param taskId
     * @return
     */
    @RequestMapping("/leaveApplication/approve")
    public String approve(@RequestParam String taskId) {
        Map<String, Object> variables = new HashMap<>(); variables.put("approved", true);
        taskService.complete(taskId, variables);
        return "approve";
    }

    /**
     * 审批驳回请假申请
     * @param taskId
     * @return
     */
    @RequestMapping("/leaveApplication/reject")
    public String reject(@RequestParam String taskId) {
        Map<String, Object> variables = new HashMap<>(); variables.put("approved", false);
        taskService.complete(taskId, variables);
        return "reject";
    }


    /**
     * 请假申请人的待办
     * @return
     */
    @RequestMapping("/leaveApplication/assignee")
    public String assignee() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("godcheese").list();
        if(tasks != null) {
            LOGGER.info("你有{}个待办", tasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());

                LOGGER.info("待办{}：taskId={}，{}, 员工：{}，想要请假{}天", i, tasks.get(i).getId(), tasks.get(i).getName(), processVariables.get("assignee"), processVariables.get("day"));
            }
        }
        return "assignee";
    }

    @RequestMapping("/leaveApplication/history")
    public String history(@RequestParam String processInstanceId) {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
           LOGGER.info("{}花费{}毫秒", activity.getActivityId() , activity.getDurationInMillis());
        }

        return "history";
    }


}