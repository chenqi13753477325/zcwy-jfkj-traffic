package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 预警告警-工程在建-吊装预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningHositingService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:36
 * @Version: 1.0
 */
public interface ConstructionWarningHositingService {
    
    /**
     * 吊装-首页-预警数，新增装机量，吊装周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:54
     */
    JSONArray warningHositing();

    /**
     * 吊装-明细-项目计划开吊率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 16:18
     */
    JSONArray pjPlanHositingRate();

    /**
     * 吊装-明细-主吊数量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 16:41
     */
    JSONArray mainCraneNumber();

    /**
     * 吊装-明细-首台齐套到货到首吊时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 16:57
     */
    JSONArray firstArrivedCompleteTime();

    /**
     * 吊装-明细-首台吊装预备齐套数
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 17:12
     */
    JSONArray firstHositingPrepareNumber();

    /**
     * 吊装-明细-主吊进场后准备时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 17:43
     */
    JSONArray mainCranePrepareTime();

    /**
     * 吊装-明细-单台主吊作业效率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 17:53
     */
    JSONArray singleCraneRate();
    
    /**
     * 吊装-明细-吊车转场效率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 18:00
     */
    JSONArray craneTransfer();

    /**
     * 吊装-明细-吊装未调试量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 18:11
     */
    JSONArray hositingNodebugNumber();


}
