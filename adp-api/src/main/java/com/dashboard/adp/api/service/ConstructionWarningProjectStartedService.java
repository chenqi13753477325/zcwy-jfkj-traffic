package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

/**
 *
 *
 * @Filename: ConstructionWarningProjectStartedService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/11/26 16:07
 * @Version: 1.0
 */
public interface ConstructionWarningProjectStartedService {

    /**
     * 开工-首页-预警数，项目计划开工率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 16:51
     */
    JSONArray cwpsCount();
    
    /**
     * 开工-明细-微观指标对应条数，比率
     * @param  [startWorkType]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 16:52
     */
    JSONArray countAndRatio(String startWorkType);

    /**
     * 开工-明细-三证完成率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 13:42
     */
    JSONArray threeEvidenceRatio();


    /**
     * 开工-明细-四标定标率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 13:42
     */
    JSONArray calibrationRatio();

    /**
     * 开工-明细-四标进场率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:22
     */
    JSONArray fourStandardRatio();

    /**
     * 开工-明细-基础施工开工率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:23
     */
    JSONArray baseCommRateRatio();
    
    /**
     * 开工-明细-机位齐套率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 16:47
     */
    JSONArray completeRateRatio();


    /**
     * 获取三证完成率对应的项目
     * @param  [threeEvidence]
     * @return
     * @date   2018/11/29 17:31
     */
    JSONArray threeEvidenceProject(Integer threeEvidence);



    /**
     * 获取四标定标率对应的项目
     * @param  [calibration]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 18:36
     */
    JSONArray calibrationProject(Integer calibration);



    /**
     * 四标进场率相关的项目
     * @param  [calibration]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 18:51
     */
    JSONArray fourStandardProject(Integer fourStandard);

    /**
     * 基础施工开工率相关的项目
     * @param  [baseCmmrate]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/3 15:26
     */
    JSONArray baseCommrateProject(Integer baseCmmrate);



}
