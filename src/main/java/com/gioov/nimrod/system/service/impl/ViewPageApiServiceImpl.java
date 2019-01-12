package com.gioov.nimrod.system.service.impl;

import com.gioov.nimrod.system.entity.ViewPageApiEntity;
import com.gioov.nimrod.system.mapper.ViewPageApiMapper;
import com.gioov.nimrod.system.service.ViewPageApiService;
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
public class ViewPageApiServiceImpl implements ViewPageApiService {

    @Autowired
    private ViewPageApiMapper viewPageApiMapper;

    @Override
    public Map<String, Object> isAssociatedByPageIdAndApiId(Long pageId, Long apiId) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("isAssociated", false);
        ViewPageApiEntity viewPageApiEntity = viewPageApiMapper.getOneByPageIdAndApiId(pageId, apiId);
        if (viewPageApiEntity != null) {
            data.put("isAssociated", true);
        }
        return data;
    }

    @Override
    public List<Long> associateAllByPageIdAndApiIdList(Long pageId, List<Long> apiIdList) {
        List<Long> apiIdList2 = new ArrayList<>();
        ViewPageApiEntity viewPageApiEntity;
        for (Long apiId : apiIdList) {
            viewPageApiEntity = viewPageApiMapper.getOneByPageIdAndApiId(pageId, apiId);
            if (viewPageApiEntity == null) {
                apiIdList2.add(apiId);
            }
        }
        if (!apiIdList2.isEmpty()) {
            viewPageApiMapper.insertAllByPageIdAndApiIdList(pageId, apiIdList2);
        }
        return apiIdList2;
    }

    @Override
    public int revokeAssociateAllByPageIdAndApiIdList(Long pageId, List<Long> apiIdList) {
        return viewPageApiMapper.deleteAllByPageIdAndApiIdList(pageId, apiIdList);
    }

}
