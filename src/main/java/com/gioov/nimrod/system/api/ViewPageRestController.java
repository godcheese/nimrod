package com.gioov.nimrod.system.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageEntity;
import com.gioov.nimrod.system.service.ViewPageService;
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
@RequestMapping(value = Url.Api.System.VIEW_PAGE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViewPageRestController {

    private static final String VIEW_PAGE = "/API/SYSTEM/VIEW_PAGE";

    @Autowired
    private ViewPageService viewPageService;

    /**
     * 指定父级视图页面分类 id ，获取所有视图页面
     *
     * @param page           页
     * @param rows           每页显示数量
     * @param pageCategoryId 视图页面分类
     * @return ResponseEntity<Pagination.Result < ViewPageEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/PAGE_ALL_BY_PAGE_CATEGORY_ID')")
    @GetMapping(value = "/page_all_by_page_category_id/{pageCategoryId}")
    public ResponseEntity<Pagination.Result<ViewPageEntity>> pageAllByPageCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @PathVariable Long pageCategoryId) {
        return new ResponseEntity<>(viewPageService.pageAllByPageCategoryId(pageCategoryId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增视图页面
     *
     * @param name           视图页面名
     * @param url            url
     * @param authority      权限（authority）
     * @param pageCategoryId 视图页面分类 id
     * @param sort           排序
     * @param remark         备注
     * @return ResponseEntity<ViewPageEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageEntity> addOne(@RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long pageCategoryId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageEntity viewPageEntity = new ViewPageEntity();
        viewPageEntity.setName(name);
        viewPageEntity.setUrl(url);
        viewPageEntity.setAuthority(authority);
        viewPageEntity.setPageCategoryId(pageCategoryId);
        viewPageEntity.setSort(sort);
        viewPageEntity.setRemark(remark);
        ViewPageEntity viewPageEntity1 = viewPageService.insertOne(viewPageEntity);
        return new ResponseEntity<>(viewPageEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面
     *
     * @param id        视图页面 id
     * @param name      视图页面名
     * @param url       url
     * @param authority 权限（authority）
     * @param sort      排序
     * @param remark    备注
     * @return ResponseEntity<ViewPageEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageEntity viewPageEntity = new ViewPageEntity();
        viewPageEntity.setId(id);
        viewPageEntity.setName(name);
        viewPageEntity.setUrl(url);
        viewPageEntity.setAuthority(authority);
        viewPageEntity.setSort(sort);
        viewPageEntity.setRemark(remark);
        ViewPageEntity viewPageEntity1 = viewPageService.updateOne(viewPageEntity);
        return new ResponseEntity<>(viewPageEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面 id ，批量删除视图页面
     *
     * @param idList 视图页面 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewPageService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图页面 id ，获取视图页面信息
     *
     * @param id 视图页面 id
     * @return ResponseEntity<ViewPageEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageService.getOne(id), HttpStatus.OK);
    }

}
