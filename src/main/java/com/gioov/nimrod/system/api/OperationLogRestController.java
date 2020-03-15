package com.gioov.nimrod.system.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.system.System;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.nimrod.system.service.OperationLogService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = System.Api.OPERATION_LOG, produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationLogRestController {

    private static final String OPERATION_LOG = "/API/SYSTEM/OPERATION_LOG";

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 分页获取所有操作日志
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination<OperationLogEntity>>
     */
    @OperationLog(value = "分页获取所有操作日志", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + OPERATION_LOG + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<OperationLogEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) throws BaseResponseException {
        return new ResponseEntity<>(operationLogService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 指定操作日志 id，获取操作日志
     * @param id 操作日志 id
     * @return ResponseEntity<OperationLogEntity2>
     */
    @OperationLog(value = "指定操作日志 id，获取操作日志", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + OPERATION_LOG + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<OperationLogEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(operationLogService.getOne(id), HttpStatus.OK);
    }

    /**
     * 清空所有操作日志
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "清空所有操作日志", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + OPERATION_LOG + "/CLEAR_ALL')")
    @PostMapping(value = "/clear_all")
    public ResponseEntity<HttpStatus> clearAll() {
        operationLogService.clearAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 指定操作日志 id，批量删除操作日志
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + OPERATION_LOG + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(operationLogService.deleteAll(idList), HttpStatus.OK);
    }
}
