package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 预警告警-工程在建-接货预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningDeliveryService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:36
 * @Version: 1.0
 */
public interface ConstructionWarningDeliveryService {
    
    /**
     * 接货-首页-预警数，接货量，接货周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:54
     */
    JSONArray warningDelivery();

    /**
     * 接货-明细-基础具备吊装条件到首个大部件到货时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 13:20
     */
    JSONArray firstArrivalTime();

    /**
     * 接货-明细-到货未吊装时长
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 14:13
     */
    JSONArray arrivalNohoistingTime();

    /**
     * 接货-明细-主机到货未吊装数量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:26
     */
    JSONArray hostArrivalNohoistingTime();

    /**
     * 接货-明细-单台塔筒平均生产周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:34
     */
    JSONArray singleTowerCycel();

    /**
     * 接货-明细-塔架到货未吊装时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:34
     */
    JSONArray towerArrivalNohoistingTime();

}
