package com.gioov.nimrod.system.service.impl;

import com.gioov.nimrod.system.entity.ViewPageComponentApiEntity;
import com.gioov.nimrod.system.mapper.ViewPageComponentApiMapper;
import com.gioov.nimrod.system.service.ViewPageComponentApiService;
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

    @Override
    public Map<String, Object> isAssociatedByPageComponentIdAndApiId(Long pageComponentId, Long apiId) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("isAssociated", false);
        ViewPageComponentApiEntity viewPageComponentApiEntity = viewPageComponentApiMapper.getOneByPageComponentIdAndApiId(pageComponentId, apiId);

        if (viewPageComponentApiEntity != null) {
            data.put("isAssociated", true);
        }
        return data;
    }

    @Override
    public List<Long> associateAllByPageComponentIdAndApiIdList(Long pageComponentId, List<Long> apiIdList) {
        List<Long> apiIdList2 = new ArrayList<>();
        ViewPageComponentApiEntity viewPageComponentApiEntity;
        for (Long apiId : apiIdList) {
            viewPageComponentApiEntity = viewPageComponentApiMapper.getOneByPageComponentIdAndApiId(pageComponentId, apiId);
            if (viewPageComponentApiEntity == null) {
                apiIdList2.add(apiId);
            }
        }
        if (!apiIdList2.isEmpty()) {
            viewPageComponentApiMapper.insertAllByPageComponentIdAndApiIdList(pageComponentId, apiIdList2);
        }
        return apiIdList2;
    }

    @Override
    public int revokeAssociateAllByPageComponentIdAndApiIdList(Long pageComponentId, List<Long> apiIdList) {
        return viewPageComponentApiMapper.deleteAllByPageComponentIdAndApiIdList(pageComponentId, apiIdList);
    }

}
