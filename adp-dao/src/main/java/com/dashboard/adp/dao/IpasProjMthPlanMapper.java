package com.dashboard.adp.dao;

import java.util.List;
import java.util.Map;

/**
 * 各阶段情况-供应资源
 *
 * @Author: c7
 * @Filename: IpasProjMthPlanMapper
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/26 15:45
 * @Version: 1.0
 */
public interface IpasProjMthPlanMapper {


    List<Map<String,Object>>  list(String contractNo);

    List<Map<String,Object>> adpPlanCarrieResourceLoad();

    List<Map<String,Object>> adpStorageLoad();

    List<Map<String,Object>> adpPlanProjectMap(String projectId);

}
