package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 各阶段情况-供应资源
 *
 * @Author: c7
 * @Filename: IpasProjMthPlanService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/26 15:41
 * @Version: 1.0
 */
public interface IpasProjMthPlanService {

    /**
     * 主需求计划版本变更
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 15:47
     */
    JSONArray list(String contractNo);

    /**
     * 承运商资源负荷
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 17:37
     */
    JSONArray adpPlanCarrieResourceLoad();

    /**
     * 工厂库容资源负荷
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 17:55
     */
    JSONArray adpCapacityTb(String month);

    /**
     * 项目分布
     * @param  [projectId]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/27 13:53
     */
    JSONArray adpPlanProjectMap(String projectId);


}
