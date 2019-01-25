package com.gioov.nimrod.system.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiEntity;
import com.gioov.nimrod.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = Url.Api.System.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRestController {

    private static final String API = "/API/SYSTEM/API";

    @Autowired
    private ApiService apiService;

    /**
     * 指定 API 分类 id ，分页获取所有 API
     *
     * @param page          页
     * @param rows          每页显示数量
     * @param apiCategoryId API 分类 id
     * @return ResponseEntity<Pagination.Result<ApiEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API + "/PAGE_ALL_BY_API_CATEGORY_ID')")
    @GetMapping(value = "/page_all_by_api_category_id/{apiCategoryId}")
    public ResponseEntity<Pagination.Result<ApiEntity>> pageAllByApiCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @PathVariable Long apiCategoryId) {
        return new ResponseEntity<>(apiService.pageAllByApiCategoryId(apiCategoryId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增 API
     *
     * @param name          API 名称
     * @param url           请求地址（url）
     * @param authority     权限（authority）
     * @param apiCategoryId API 分类 id
     * @param sort          排序
     * @param remark        备注
     * @return ResponseEntity<ApiEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ApiEntity> addOne(@RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long apiCategoryId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ApiEntity apiEntity = new ApiEntity();
        apiEntity.setName(name);
        apiEntity.setUrl(url);
        apiEntity.setAuthority(authority);
        apiEntity.setApiCategoryId(apiCategoryId);
        apiEntity.setSort(sort);
        apiEntity.setRemark(remark);
        ApiEntity apiEntity1 = apiService.insertOne(apiEntity);
        return new ResponseEntity<>(apiEntity1, HttpStatus.OK);
    }

    /**
     * 保存 API
     *
     * @param id     API id
     * @param name   API 名称
     * @param url    请求地址（url）
     * @param sort   排序
     * @param remark 备注
     * @return ResponseEntity<ApiEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ApiEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long sort, @RequestParam String remark) {
        ApiEntity apiEntity = new ApiEntity();
        apiEntity.setId(id);
        apiEntity.setName(name);
        apiEntity.setUrl(url);
        apiEntity.setAuthority(authority);
        apiEntity.setSort(sort);
        apiEntity.setRemark(remark);
        ApiEntity apiEntity1 = apiService.updateOne(apiEntity);
        return new ResponseEntity<>(apiEntity1, HttpStatus.OK);
    }

    /**
     * 指定 API id list ，批量删除 API
     *
     * @param idList API id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(apiService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定 API id ， 获取 API 信息
     *
     * @param id API id
     * @return ResponseEntity<ApiEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ApiEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(apiService.getOne(id), HttpStatus.OK);
    }

}
