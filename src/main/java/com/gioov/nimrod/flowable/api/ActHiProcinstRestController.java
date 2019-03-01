package com.gioov.nimrod.flowable.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.flowable.Flowable;
import com.gioov.nimrod.flowable.entity.ActHiProcinstEntity;
import com.gioov.nimrod.flowable.service.ActHiProcinstService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@RequestMapping(Flowable.Api.ACT_HI_PROCINST)
@RestController
public class ActHiProcinstRestController {

    public static final String ACT_HI_PROCINST = "/API/FLOWABLE/ACT_HI_PROCINST";

    @Autowired
    private ActHiProcinstService actHiProcinstService;

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
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ACT_HI_PROCINST + "/PAGE_ALL_ACTIVE')")
    @GetMapping(value = "/page_all_active", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Pagination.Result<ActHiProcinstEntity> > pageAllActive(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(actHiProcinstService.pageAll(page, rows, true), HttpStatus.OK);
    }

    /**
     * 指定队列邮件 id ，批量删除队列邮件
     *
     * @param idList 电子邮件 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ACT_HI_PROCINST + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id_[]") List<String> idList,@RequestParam String deleteReason) {
        List<String> deleteReasonList = new ArrayList<>();
        int i;
        for(i=0; i< idList.size(); i++) {
            deleteReasonList.add(deleteReason);
        }
        return new ResponseEntity<>(actHiProcinstService.deleteProcessInstance(idList, deleteReasonList), HttpStatus.OK);
    }

}
