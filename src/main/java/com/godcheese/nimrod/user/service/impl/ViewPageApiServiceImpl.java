package com.godcheese.nimrod.user.service.impl;

import com.godcheese.nimrod.user.entity.ViewPageApiEntity;
import com.godcheese.nimrod.user.mapper.ViewPageApiMapper;
import com.godcheese.nimrod.user.service.ViewPageApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewPageApiServiceImpl implements ViewPageApiService {

    @Autowired
    private ViewPageApiMapper viewPageApiMapper;

//    @Override
//    public Map<String, Object> isAssociatedByViewPageIdAndApiId(Long viewPageId, Long apiId) {
//        Map<String, Object> data = new HashMap<>(1);
//        data.put("isAssociated", false);
//        ViewPageApiEntity viewPageApiEntity = viewPageApiMapper.getOneByViewPageIdAndApiId(viewPageId, apiId);
//        if (viewPageApiEntity != null) {
//            data.put("isAssociated", true);
//        }
//        return data;
//    }

    @Override
    public int associateAllByViewPageIdAndApiIdList(Long viewPageId, List<Long> apiIdList) {
        List<Long> apiIdList2 = new ArrayList<>();
        ViewPageApiEntity viewPageApiEntity;
        for (Long apiId : apiIdList) {
            viewPageApiEntity = viewPageApiMapper.getOneByViewPageIdAndApiId(viewPageId, apiId);
            if (viewPageApiEntity == null) {
                apiIdList2.add(apiId);
            }
        }
        if (!apiIdList2.isEmpty()) {
            viewPageApiMapper.insertAllByViewPageIdAndApiIdList(viewPageId, apiIdList2);
        }
        return apiIdList2.size();
    }

    @Override
    public int revokeAssociateAllByViewPageIdAndApiIdList(Long viewPageId, List<Long> apiIdList) {
        return viewPageApiMapper.deleteAllByViewPageIdAndApiIdList(viewPageId, apiIdList);
    }

}
