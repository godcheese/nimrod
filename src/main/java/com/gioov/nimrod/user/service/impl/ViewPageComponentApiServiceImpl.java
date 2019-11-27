package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.user.entity.ViewPageComponentApiEntity;
import com.gioov.nimrod.user.mapper.ViewPageComponentApiMapper;
import com.gioov.nimrod.user.service.ViewPageComponentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewPageComponentApiServiceImpl implements ViewPageComponentApiService {

    @Autowired
    private ViewPageComponentApiMapper viewPageComponentApiMapper;

//    @Override
//    public Map<String, Object> isAssociatedByViewPageComponentIdAndApiId(Long viewPageComponentId, Long apiId) {
//        Map<String, Object> data = new HashMap<>(1);
//        data.put("isAssociated", false);
//        ViewPageComponentApiEntity viewPageComponentApiEntity = viewPageComponentApiMapper.getOneByViewPageComponentIdAndApiId(viewPageComponentId, apiId);
//
//        if (viewPageComponentApiEntity != null) {
//            data.put("isAssociated", true);
//        }
//        return data;
//    }

    @Override
    public int associateAllByViewPageComponentIdAndApiIdList(Long viewPageComponentId, List<Long> apiIdList) {
        List<Long> apiIdList2 = new ArrayList<>();
        ViewPageComponentApiEntity viewPageComponentApiEntity;
        for (Long apiId : apiIdList) {
            viewPageComponentApiEntity = viewPageComponentApiMapper.getOneByViewPageComponentIdAndApiId(viewPageComponentId, apiId);
            if (viewPageComponentApiEntity == null) {
                apiIdList2.add(apiId);
            }
        }
        if (!apiIdList2.isEmpty()) {
            viewPageComponentApiMapper.insertAllByViewPageComponentIdAndApiIdList(viewPageComponentId, apiIdList2);
        }
        return apiIdList2.size();
    }

    @Override
    public int revokeAssociateAllByViewPageComponentIdAndApiIdList(Long viewPageComponentId, List<Long> apiIdList) {
        return viewPageComponentApiMapper.deleteAllByViewPageComponentIdAndApiIdList(viewPageComponentId, apiIdList);
    }

}
