package com.dashboard.adp.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各阶段-生产
 *
 * @Author: c7
 * @Filename: AdpCapacityMapper
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/25 12:01
 * @Version: 1.0
 */
public interface AdpCapacityMapper {

    HashMap fdjTotal(@Param(value="sortMap")Map<String,Object> map);

    HashMap jcTotal(@Param(value="sortMap")Map<String,Object> map);

    HashMap ylTotal(@Param(value="sortMap")Map<String,Object> map);

    HashMap ypTotal(@Param(value="sortMap")Map<String,Object> map);


    List<Map<String,Object>> fdjMap(@Param(value="sortMap")Map<String,Object> map);

    List<Map<String,Object>> jcMap(@Param(value="sortMap")Map<String,Object> map);

    List<Map<String,Object>> ylMap(@Param(value="sortMap")Map<String,Object> map);

    List<Map<String,Object>> ypMap(@Param(value="sortMap")Map<String,Object> map);

    Long sumAdpCapacityFdj(@Param("monthStr") String monthStr);

    Long sumAdpCapacityJc(@Param("monthStr") String monthStr);
    Long sumAdpCapacityYl(@Param("monthStr") String monthStr);
    Long sumAdpCapacityYp(@Param("monthStr") String monthStr);


}
