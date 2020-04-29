package com.dashboard.adp.api.service;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface BaseDataService {

    /**
     * 获取国家数据
     * @return
     */
    List<Map<String,Object>> getCountry();

    /**
     * 获取区域数据
     * @return
     */
    List<Map<String,Object>> getRegionInfos();

    /**
     * 获取省份数据
     * @return
     */
    List<Map<String,Object>> getProvinces(String  regions,String userName);

    /**
     * 获取企业性质数据
     * @return
     */
    List<Map<String,Object>> getEnterpriseNature(String userName);

    /**
     * 获取客户数据
     * @return
     */
    List<Map<String,Object>> getCustomer(String enterpriseNatures,String userName);

    /**
     *查询所有客户事业部
     * @return
     */
    List<Map<String,Object>> getDepartment(String userName);

    /**
     *查询所有销售主体
     * @return
     */
    List<Map<String,Object>> getSaleSubject(String userName);

    /**
     * 查询所有销售事业部
     * @param saleComps
     */
    List<Map<String,Object>> getSaleDept(String saleComps,String userName);

    /**
     *查询所有机型平台
     * @return
     */
    List<Map<String,Object>> queryModelPlatforms();

    /**
     *查询所有机型
     * @return
     */
    List<Map<String,Object>> getContrmodModels(String platforms,String customer);

    /**
     *获取token
     * @return
     */
    String getToken() throws IOException;

    /**
     *获取天气预报
     * @return
     */
    JSONArray getfutureWeather(String projectId) throws IOException, ParseException;

    /**
     *获取当天天气预报
     * @return
     */
    JSONArray getSameDayWeather(String contractno) throws IOException, ParseException;


    /**
     *获取百度4天天气预报
     * @return
     */
    JSONArray getBaiduWeather(String contractno,String key);


    /**
     * 查询所有机组环境
     * @return
     */
    List<Map<String,Object>> getEnvironments(String  terrains);


    /**
     * 查询所有地形
     * @return
     */
    List<Map<String,Object>> getTerrains();

    /**
     * 查询所有风区
     * @return
     */
    List<Map<String,Object>> getWindResources();

    /**
     * 查询所有承运商
     * @return
     */
    List<Map<String,Object>> getSuppliers();

    /**
     * 查询所有工厂类型
     * @return
     */
    List<Map<String,Object>> getFactoryTypes();

    /**
     * 查询所有工厂
     * @return
     */
    List<Map<String,Object>> getFactorys(String  factorytypes);

    /**
     * 查询所有生产大部件类型
     * @return
     */
    List<Map<String,Object>> getLargePartTypes();

    /**
     * 查询所有生产大部件
     * @return
     */
    List<Map<String,Object>> getLargeParts(String  parttypes);

    /**
     * 查询所有采购关键部件类型
     * @return
     */
    List<Map<String,Object>> getKeyLargePartTypes();

    /**
     * 查询所有采购关键部件
     * @return
     */
    List<Map<String,Object>> getKeyLargeParts(String  keyparttypes);


    int updateWarnList(Map<String, Object> map);

    /**
     * 获取所有预警分类
     * @return
     */
    List<Map<String,Object>> getWarnTypes();

    /**
     * 获取指标列表
     * @param
     * @return
     */
    List<Map<String,Object>> getQuotaList(String warntypes);

    /**
     * 获取保障类型
     * @return
     */
    List<Map<String,Object>> protectiontype();

    /**
     * 保存预警-预警清单合集
     * @param adpWarningListMap
     * @return
     */
    int saveAdpWarningList(Map<String,Object> adpWarningListMap);

    /**
     * 删除预警-预警清单合集
     * @param id
     */
    int delAdpWarningList(String id);

    /**
     * 根据预警分类获取预警指标
     * @param warnlo
     * @return
     */
    List<Map<String,Object>> getWarnByWarnlo(String warnlo);

    /**
     * @Description: 采购-日报报缺日报-工厂列表
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Date: 2020/4/21 14:05
     */
    List<Map<String,Object>> selFactoryName();
    /*
     * 功能描述: <br>
     * @Param: 单项目不同月份视频列表
     * @Return: 
     * @Author: lihx
     * @Date: 2019/12/18 11:32
     */
//    List<Map<String, Object>> videoList(Map<String, String> map);
    
    /**
     * 功能描述: <br>
     * @Param: 保存视频，记录视频相关信息
     * @Return: 
     * @Author: lihx
     * @Date: 2019/12/18 15:25
     */
    void saveVideoInfo(Map paramMap);

    /**
     * @Description: 各阶段情况-计划页面，模板生产
     * @Param: [params]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Date: 2020/4/21 14:05
     */
    List<Map<String,Object>> templetList(Map<String,Object> params);

}
