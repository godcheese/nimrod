package com.gioov.example.api;

import com.gioov.common.web.http.SuccessEntity;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping("/api/example")
public class ExampleRestController {

    private static final String EXAMPLE = "/EXAMPLE";

    /**
     * 测试 API
     *
     * @return String
     */
    @OperationLog(value = "example test", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + EXAMPLE + "/TEST')")
    @RequestMapping("/test")
    public ResponseEntity<SuccessEntity> test() {
        return new ResponseEntity<>(new SuccessEntity(HttpStatus.OK), HttpStatus.OK);
    }

}
