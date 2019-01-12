package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.system.entity.*;
import com.gioov.nimrod.system.mapper.*;
import com.gioov.nimrod.user.entity.RoleAuthorityEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private ViewPageMapper viewPageMapper;

    @Autowired
    private ViewPageApiMapper viewPageApiMapper;

    @Autowired
    private ApiMapper apiMapper;

    @Autowired
    private ViewPageComponentMapper viewPageComponentMapper;

    @Autowired
    private ViewPageComponentApiMapper viewPageComponentApiMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> grantAllByRoleIdAndApiAuthorityList(Long roleId, List<String> authorityList) {
        // API authority
        List<String> apiAuthorityList = new ArrayList<>();

        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();

        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                apiAuthorityList.add(authority);
            }
        }

        RoleAuthorityEntity roleAuthorityEntity;
        for (String a : apiAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity == null) {
                authorityList3.add(a);
            }
        }

        // authority 全部写入数据库
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.insertAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> revokeAllByRoleIdAndApiAuthorityList(Long roleId, List<String> authorityList) {
        // API authority
        List<String> apiAuthorityList = new ArrayList<>();
        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();
        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                apiAuthorityList.add(authority);
            }
        }
        RoleAuthorityEntity roleAuthorityEntity;
        for (String a : apiAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity != null) {
                authorityList3.add(a);
            }
        }
        // authority 全部删除
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.deleteAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> grantAllByRoleIdAndPageAuthorityList(Long roleId, List<String> authorityList) {
        // 视图页面 authority
        List<String> pageAuthorityList = new ArrayList<>();
        // 视图页面组件关联的 API authority
        List<String> apiAuthorityList = new ArrayList<>();
        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();
        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                pageAuthorityList.add(authority);

                ViewPageEntity viewPageEntity = viewPageMapper.getOneByAuthority(authority);
                if (viewPageEntity != null) {
                    List<ViewPageApiEntity> viewPageApiEntityList = viewPageApiMapper.listAllByPageId(viewPageEntity.getId());
                    if (viewPageApiEntityList != null && !viewPageApiEntityList.isEmpty()) {

                        for (ViewPageApiEntity viewPageApiEntity : viewPageApiEntityList) {
                            ApiEntity apiEntity = apiMapper.getOne(viewPageApiEntity.getApiId());
                            if (apiEntity != null) {
                                apiAuthorityList.add(apiEntity.getAuthority());
                            }
                        }
                    }
                }
            }
        }
        // 视图页面关联的 API authority 全部放入 pageAuthorityList
        pageAuthorityList.addAll(apiAuthorityList);
        RoleAuthorityEntity roleAuthorityEntity;
        for (String a : pageAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity == null) {
                authorityList3.add(a);
            }
        }
        // authority 全部写入数据库
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.insertAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> revokeAllByRoleIdAndPageAuthorityList(Long roleId, List<String> authorityList) {
        // 视图页面 authority
        List<String> pageAuthorityList = new ArrayList<>();
        // 视图页面组件关联的 API authority
        List<String> apiAuthorityList = new ArrayList<>();
        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();
        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                pageAuthorityList.add(authority);
                ViewPageEntity viewPageEntity = viewPageMapper.getOneByAuthority(authority);
                if (viewPageEntity != null) {
                    List<ViewPageApiEntity> viewPageApiEntityList = viewPageApiMapper.listAllByPageId(viewPageEntity.getId());
                    if (viewPageApiEntityList != null && !viewPageApiEntityList.isEmpty()) {
                        for (ViewPageApiEntity viewPageApiEntity : viewPageApiEntityList) {
                            ApiEntity apiEntity = apiMapper.getOne(viewPageApiEntity.getApiId());
                            if (apiEntity != null) {
                                apiAuthorityList.add(apiEntity.getAuthority());
                            }
                        }
                    }
                }
            }
        }
        // 视图页面组件关联的 API authority 全部放入 pageAuthorityList
        pageAuthorityList.addAll(apiAuthorityList);
        RoleAuthorityEntity roleAuthorityEntity = null;
        for (String a : pageAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity != null) {
                authorityList3.add(a);
            }
        }
        // authority 全部删除
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.deleteAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> grantAllByRoleIdAndPageComponentAuthorityList(Long roleId, List<String> authorityList) {
        // 视图页面组件 authority
        List<String> pageComponentAuthorityList = new ArrayList<>();
        // 视图页面组件关联的 API authority
        List<String> apiAuthorityList = new ArrayList<>();
        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();
        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                pageComponentAuthorityList.add(authority);
                ViewPageComponentEntity viewPageComponentEntity = viewPageComponentMapper.getOneByAuthority(authority);
                if (viewPageComponentEntity != null) {
                    List<ViewPageComponentApiEntity> viewPageComponentApiEntityList = viewPageComponentApiMapper.listAllByPageComponentId(viewPageComponentEntity.getId());
                    if (viewPageComponentApiEntityList != null && !viewPageComponentApiEntityList.isEmpty()) {
                        for (ViewPageComponentApiEntity viewPageApiEntity : viewPageComponentApiEntityList) {
                            ApiEntity apiEntity = apiMapper.getOne(viewPageApiEntity.getApiId());
                            if (apiEntity != null) {
                                apiAuthorityList.add(apiEntity.getAuthority());
                            }
                        }
                    }
                }
            }
        }
        // 视图页面组件关联的 API authority 全部放入 pageComponentAuthorityList
        pageComponentAuthorityList.addAll(apiAuthorityList);
        RoleAuthorityEntity roleAuthorityEntity = null;
        for (String a : pageComponentAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity == null) {
                authorityList3.add(a);
            }
        }
        // authority 全部写入数据库
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.insertAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> revokeAllByRoleIdAndPageComponentAuthorityList(Long roleId, List<String> authorityList) {
        // 视图页面组件 authority
        List<String> pageComponentAuthorityList = new ArrayList<>();
        // 视图页面组件关联的 API authority
        List<String> apiAuthorityList = new ArrayList<>();
        // 最终被添加的 authority
        List<String> authorityList3 = new ArrayList<>();
        for (String authority : authorityList) {
            if (!"".equals(authority.trim())) {
                pageComponentAuthorityList.add(authority);

                ViewPageComponentEntity viewPageComponentEntity = viewPageComponentMapper.getOneByAuthority(authority);
                if (viewPageComponentEntity != null) {
                    List<ViewPageComponentApiEntity> viewPageComponentApiEntityList = viewPageComponentApiMapper.listAllByPageComponentId(viewPageComponentEntity.getId());
                    if (viewPageComponentApiEntityList != null && !viewPageComponentApiEntityList.isEmpty()) {

                        for (ViewPageComponentApiEntity viewPageApiEntity : viewPageComponentApiEntityList) {
                            ApiEntity apiEntity = apiMapper.getOne(viewPageApiEntity.getApiId());
                            if (apiEntity != null) {
                                apiAuthorityList.add(apiEntity.getAuthority());
                            }
                        }
                    }
                }

            }
        }
        // 视图页面组件关联的 API authority 全部放入 pageComponentAuthorityList
        pageComponentAuthorityList.addAll(apiAuthorityList);
        RoleAuthorityEntity roleAuthorityEntity = null;
        for (String a : pageComponentAuthorityList) {
            roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, a);
            if (roleAuthorityEntity != null) {
                authorityList3.add(a);
            }
        }
        // authority 全部删除
        if (!authorityList3.isEmpty()) {
            roleAuthorityMapper.deleteAllByRoleIdAndAuthorityList(roleId, authorityList3);
        }
        return authorityList3;
    }

    @Override
    public Map<String, Object> isGrantedByRoleIdAndAuthority(Long roleId, String authority) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("isGranted", false);
        RoleAuthorityEntity roleAuthorityEntity = roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, authority);
        if (roleAuthorityEntity != null) {
            data.put("isGranted", true);
        }
        return data;
    }

    @Override
    public RoleAuthorityEntity getOne(Long id) {
        return roleAuthorityMapper.getOne(id);
    }
}
