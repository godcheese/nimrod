package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ApiCategoryEntity;
import com.gioov.nimrod.user.service.ApiCategoryService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.API_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiCategoryRestController {

    private static final String API_CATEGORY = "/API/SYSTEM/API_CATEGORY";

    @Autowired
    private ApiCategoryService apiCategoryService;

    /**
     * 获取所有父级 API 分类
     * @return List<ApiCategoryEntity>
     */
    @OperationLog(value = "分页获取所有父级 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/LIST_ALL_PARENT')")
    @GetMapping(value = "/list_all_parent")
    public ResponseEntity<List<ApiCategoryEntity>> listAllParent() {
        return new ResponseEntity<>(apiCategoryService.listAllParent(), HttpStatus.OK);
    }

    /**
     * 指定父级 API 分类 id，获取所有 API 分类
     * @param parentId API 分类父级 id
     * @return ResponseEntity<List<ApiCategoryEntity>>
     */
    @OperationLog(value = "获取所有 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id")
    public ResponseEntity<List<ApiCategoryEntity>> listAllByParentId(@RequestParam Long parentId) {
        return new ResponseEntity<>(apiCategoryService.listAllByParentId(parentId), HttpStatus.OK);
    }

    /**
     * 新增 API 分类
     * @param name API 分类名称
     * @param parentId API 分类父级 id
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ApiCategoryEntity>
     */
    @OperationLog(value = "新增 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ApiCategoryEntity> addOne(@RequestParam String name, @RequestParam Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        ApiCategoryEntity apiCategoryEntity = new ApiCategoryEntity();
        apiCategoryEntity.setName(name);
        apiCategoryEntity.setParentId(parentId);
        apiCategoryEntity.setSort(sort);
        apiCategoryEntity.setRemark(remark);
        ApiCategoryEntity apiCategoryEntity1 = apiCategoryService.addOne(apiCategoryEntity);
        return new ResponseEntity<>(apiCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 保存 API 分类
     * @param id API 分类 id
     * @param name API 分类名称
     * @param parentId API 分类父级 id
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ApiCategoryEntity>
     */
    @OperationLog(value = "保存 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ApiCategoryEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long parentId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ApiCategoryEntity apiCategoryEntity = new ApiCategoryEntity();
        apiCategoryEntity.setId(id);
        apiCategoryEntity.setName(name);
        apiCategoryEntity.setParentId(parentId);
        apiCategoryEntity.setSort(sort);
        apiCategoryEntity.setRemark(remark);
        ApiCategoryEntity apiCategoryEntity1 = apiCategoryService.saveOne(apiCategoryEntity);
        return new ResponseEntity<>(apiCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 指定 API 分类 id list，批量删除 API 分类
     * @param idList API 分类 id list
     * @return ResponseEntity<Integer>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "指定 API 分类 id list，批量删除 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(apiCategoryService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定 API 分类 id，获取所有 API 分类
     * @param id API 分类 id
     * @return ResponseEntity<ApiCategoryEntity>
     */
    @OperationLog(value = "指定 API 分类 id，获取所有 API 分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ApiCategoryEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(apiCategoryService.getOne(id), HttpStatus.OK);
    }
    /**
     * 获取所有 API 分类，以 ComboTree 形式展示
     * @return ResponseEntity<List<ComboTree>>
     */
    @OperationLog(value = "获取所有 API 分类，以 ComboTree 形式展示", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/LIST_ALL_AS_COMBO_TREE')")
    @GetMapping(value = "/list_all_as_combo_tree")
    public ResponseEntity<List<ComboTree>> listAllAsComboTree() {
        List<ComboTree> comboTreeResultList = new ArrayList<>();
        List<ComboTree> apiCategoryComboTreeList = apiCategoryService.listAllApiCategoryComboTree();
        for(ComboTree comboTree : apiCategoryComboTreeList) {
            if(comboTree.getParentId() == null) {
                comboTreeResultList.add(comboTree);
            }
        }
        for(ComboTree comboTree : comboTreeResultList) {
            comboTree.setChildren(apiCategoryService.getApiCategoryChildrenComboTree(comboTree.getId(), apiCategoryComboTreeList));
        }
        return new ResponseEntity<>(comboTreeResultList, HttpStatus.OK);
    }
}
