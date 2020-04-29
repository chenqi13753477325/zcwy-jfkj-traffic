package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 各阶段-生产
 *
 * @Author: c7
 * @Filename: AdpCapacityService
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/25 11:58
 * @Version: 1.0
 */
public interface AdpCapacityService {

    /**
     * 页片总产能，机舱总产能，叶轮总产能，电控总产能
     * @param  [month]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 12:10
     */
    JSONArray totalCapacity(String dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                            String factory, String largeparttype, String largepart);

    /**
     * 地图产能分布
     * @param  [month]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 16:03
     */
    JSONArray mapCapacity(String dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                          String factory, String largeparttype, String largepart);

    /**
     * 产能列表
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 17:53
     */
    JSONArray listCapacity(String dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                           String factory, String largeparttype, String largepart);

}
