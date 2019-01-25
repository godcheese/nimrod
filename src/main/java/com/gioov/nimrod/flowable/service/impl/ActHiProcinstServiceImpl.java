package com.gioov.nimrod.flowable.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.flowable.entity.ActHiProcinstEntity;
import com.gioov.nimrod.flowable.mapper.ActHiProcinstMapper;
import com.gioov.nimrod.flowable.service.ActHiProcinstService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@Service
public class ActHiProcinstServiceImpl implements ActHiProcinstService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActHiProcinstServiceImpl.class);
    @Autowired
    private ActHiProcinstMapper actHiProcinstMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private Common common;

    @Override
    public int deleteProcessInstance(List<String> idList, List<String> deleteReasonList) {
        int count = 0;
        for(String id : idList) {
            deleteProcessInstance(id, deleteReasonList.get(count));
            count++;
        }
        return count;
    }

    @Override
    public void deleteProcessInstance(String id, String deleteReason) {
        runtimeService.deleteProcessInstance(id, deleteReason);
    }

    @Override
    public Pagination.Result<ActHiProcinstEntity> pageAll(int page, int rows, boolean active) {

        List<ActHiProcinstEntity> actHiProcinstEntityList = null;
        Pagination.Result<ActHiProcinstEntity> paginationResult = new Pagination().new Result<>();

//        List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().listPage(first,max);
//        for(ProcessInstance processInstance : processInstanceList) {
//        }

        actHiProcinstEntityList = actHiProcinstMapper.pageAllActive(new Pageable(page, rows));

        if (actHiProcinstEntityList != null) {
            paginationResult.setRows(actHiProcinstEntityList);
        }
//        paginationResult.setTotal(actHiProcinstMapper.countAllActive());
        return paginationResult;


//       Pagination.Result<ActHiProcinstEntity> paginationResult = new Pagination().new Result<>();
//        List<ActHiProcinstEntity> actHiProcinstEntityList;
//        int total;
//       if(active) {
//         actHiProcinstEntityList = actHiProcinstMapper.pageAllActive(new Pageable(page, rows));
//         total = actHiProcinstMapper.countAll();
//       } else {
//           actHiProcinstEntityList = actHiProcinstMapper.pageAll(new Pageable(page, rows));
//           total = actHiProcinstMapper.countAllActive();
//       }
//       if(actHiProcinstEntityList != null) {
//           actHiProcinstEntityResult.setRows(actHiProcinstEntityList);
//           actHiProcinstEntityResult.setTotal(total);
//       }
//        return paginationResult;
    }

}
