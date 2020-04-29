package com.dashboard.adp.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 各阶段情况-库存
 *
 * @Author: c7
 * @Filename: AdpStockDelayMapper
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/11 19:39
 * @Version: 1.0
 */
public interface AdpStockDelayMapper {

    /**
     * 呆滞品超期列表
     * @param  []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/12/11 19:42
     */
    List<Map<String,Object>> adpStockDelayList();

    /**
     * 库容负载状态
     * @param  []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/12/12 14:22
     */
    List<Map<String,Object>> adpStorageLoad();

    /**
     * 库存资源分布图-总装厂
     * @param  []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/12/12 17:47
     */
    List<Map<String,Object>> assemblyPlant(@Param(value="sortMap")Map<String,Object> map);

    /**
     * 总装厂关联的大部件
     * @param  []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/12/12 18:22
     */
    List<Map<String,Object>> largePartsTypeCount(String factoryCode);

}
