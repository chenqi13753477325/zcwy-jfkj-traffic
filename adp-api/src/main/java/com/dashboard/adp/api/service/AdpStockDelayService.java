package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;
/**
 * 各阶段情况-库存
 *
 * @Author: c7
 * @Filename: AdpStockDelayService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/11 19:36
 * @Version: 1.0
 */
public interface AdpStockDelayService {

    /**
     * 呆滞品超期集合
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/12 14:19
     */
    JSONArray adpStockDelayList();

    /**
     * 库容负载状态
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/12 14:19
     */
    JSONArray adpStorageLoad();

    /**
     * 库存资源分布图
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/12 17:40
     */
    JSONArray adpStockLargePartsAge(String modelPlats, String machineType, String factorytype,
                                    String factory, String largeparttype, String largepart);

}
