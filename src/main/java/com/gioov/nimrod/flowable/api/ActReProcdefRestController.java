package com.gioov.nimrod.flowable.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.flowable.Flowable;
import com.gioov.nimrod.flowable.entity.ActReProcdefEntity;
import com.gioov.nimrod.flowable.service.ActReProcdefService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@RequestMapping(Flowable.Api.ACT_RE_PROCDEF)
@RestController
public class ActReProcdefRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActReProcdefRestController.class);

    public static final String ACT_RE_PROCDEF = "/API/FLOWABLE/ACT_RE_PROCDEF";

    @Autowired
    private ActReProcdefService actReProcDefService;

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
     * 分页获取所有父级 API 分类
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<ApiCategoryEntity>
     */
    @OperationLog(value = "分页获取所有 Flowable 流程定义", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ACT_RE_PROCDEF + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination.Result<ActReProcdefEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(actReProcDefService.pageAll(page, rows), HttpStatus.OK);
    }

    @RequestMapping(value = "/process_diagram")
    public void processDiagram(HttpServletResponse httpServletResponse, @RequestParam(required = false) String processInstanceId, @RequestParam(required = false) String processDefinitionId) throws Exception {

        if (processInstanceId != null) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

            // 流程走完的不显示图
            if (processInstance == null) {
                return;
            }

            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            // 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
            String InstanceId = task.getProcessInstanceId();
            List<Execution> executions = runtimeService
                    .createExecutionQuery()
                    .processInstanceId(InstanceId)
                    .list();

            // 得到正在执行的Activity的Id
            List<String> highLightedActivities = new ArrayList<>();
            List<String> highLightedFlows = new ArrayList<>();
            for (Execution execution : executions) {
                List<String> idList = runtimeService.getActiveActivityIds(execution.getId());
                highLightedActivities.addAll(idList);
            }

            // 获取流程图
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            byte[] buf = new byte[1024];
            int length = 0;

            try (InputStream inputStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows, processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0, true); OutputStream outputStream = httpServletResponse.getOutputStream()) {
                while ((length = inputStream.read(buf)) != -1) {
                    LOGGER.info("{}", outputStream);
                    outputStream.write(buf, 0, length);
                }
            }

        } else {
            if(processDefinitionId != null) {
                BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
                ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
                ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
                byte[] buf = new byte[1024];
                int length = 0;

                try (InputStream inputStream = diagramGenerator.generateDiagram(bpmnModel, "png", processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0, true); OutputStream out = httpServletResponse.getOutputStream()) {
                    while ((length = inputStream.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                }
            }
        }


    }

    /**
     * 指定队列邮件 id ，批量删除队列邮件
     *
     * @param idList 电子邮件 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ACT_RE_PROCDEF + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id_[]") List<String> idList) {
        return new ResponseEntity<>( actReProcDefService.deleteProcessDefinition(idList), HttpStatus.OK);
    }

}
