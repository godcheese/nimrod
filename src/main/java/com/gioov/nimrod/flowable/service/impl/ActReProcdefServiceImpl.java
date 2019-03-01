package com.gioov.nimrod.flowable.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.flowable.entity.ActReProcdefEntity;
import com.gioov.nimrod.flowable.mapper.ActReProcdefMapper;
import com.gioov.nimrod.flowable.service.ActReProcdefService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@Service
public class ActReProcdefServiceImpl implements ActReProcdefService {

    @Autowired
    private ActReProcdefMapper actReProcDefMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;

    @Override
    public Pagination.Result<ActReProcdefEntity> pageAll(Integer page, Integer rows) {
        int max = 10;
        page = page == 0 ? 1 : page;
        int first = (page - 1) * max;
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().listPage(first, max);
        Pagination.Result<ActReProcdefEntity> paginationResult = new Pagination().new Result<>();
        List<ActReProcdefEntity> actReProcDefEntityList = actReProcDefMapper.pageAll(new Pageable(page, rows));
        if (actReProcDefEntityList != null) {
            paginationResult.setRows(actReProcDefEntityList);
        }
        paginationResult.setTotal(actReProcDefMapper.countAll());
        return paginationResult;
    }

    @Override
    public int deleteProcessDefinition(List<String> idList) {
        int count = 0;
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionIds(new HashSet<String>(idList)).list();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
            count++;
        }
        return count;
    }
}
