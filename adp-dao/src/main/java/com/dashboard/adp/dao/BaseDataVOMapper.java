package com.dashboard.adp.dao;
import com.dashboard.adp.api.bean.AdJinFengWeather;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface BaseDataVOMapper {

    /**
     * 查询所有国家
     * @return
     */
    List<Map<String,Object>> queryCountry();

    /**
     * 获取所有区域
     * @return
     */
    List<Map<String,Object>> queryRegion();
    /**
     * 获取所有省份
     * @return
     */
    List<Map<String,Object>> queryProvince(@Param("regions") String  regions,
                                           @Param("roleId") String  roleId,
                                           @Param("userName") String  userName);

    /**
     * 获取所有企业性质
     * @return
     */
    List<Map<String,Object>> queryEnterpriseNatures(@Param("roleId") String  roleId,
                                                    @Param("userName") String  userName);

    /**
     * 查询所有客户
     * @return
     */
    List<Map<String,Object>> queryCustomer(@Param("pjCustnature") String  pjCustnature,
                                           @Param("roleId") String  roleId,
                                           @Param("userName") String  userName);

    /**
     * 查询所有执行主体
     * @return
     */
    List<Map<String,Object>> queryDepartment(@Param("roleId") String  roleId,
                                             @Param("userName") String  userName);

    /**
     * 查询所有销售主体
     */
    List<Map<String,Object>> querySaleSubject(@Param("roleId") String  roleId,
                                              @Param("userName") String  userName);

    /**
     * 查询所有销售事业部
     * @param saleComps 多个销售主体以逗号分隔的字符串，例如：'',...'n'
     * @return
     */
    List<Map<String,Object>> querySaleDept(@Param("saleComps") String  saleComps,
                                           @Param("roleId") String  roleId,
                                           @Param("userName") String  userName);

    //查询所有机型平台
    List<Map<String,Object>> queryModelPlatforms();


    //查询所有机型
    List<Map<String,Object>> queryContrmodModels(@Param("platforms") String  platforms,@Param("customer") String  customer);

    //查询经纬度
    List<AdJinFengWeather> queryLongLat(String contractno);

    /**
     * 查询所有机组环境
     * @return
     */
    List<Map<String,Object>> queryEnvironments(@Param("terrains") String  terrains);

    /**
     * 查询所有地形
     * @return
     */
    List<Map<String,Object>> queryTerrains();

    /**
     * 查询所有风区
     * @return
     */
    List<Map<String,Object>> queryWindResources();

    /**
     * 查询所有承运商
     * @return
     */
    List<Map<String,Object>> querySuppliers();
    /**
     * 查询所有工厂类型
     * @return
     */
    List<Map<String,Object>> queryFactoryTypes();

    /**
     * 查询所有工厂
     * @return
     */
    List<Map<String,Object>> queryFactorys(@Param("factorytypes") Map<String,Object>  factorytypes);

    /**
     * 查询所有生产大部件类型
     * @return
     */
    List<Map<String,Object>> queryLargePartTypes();

    /**
     * 查询所有生产大部件
     * @return
     */
    List<Map<String,Object>> queryLargeParts(@Param("parttypes") String  parttypes);

    /**
     * 查询所有采购关键部件类型
     * @return
     */
    List<Map<String,Object>> queryKeyLargePartTypes();

    /**
     * 查询所有采购关键部件
     * @return
     */
    List<Map<String,Object>> queryKeyLargeParts(@Param("keyparttypes") String  keyparttypes);

    int updateWarnList(Map<String, Object> map);

    /**
     * 获取所有预警分类
     * @return
     */
    List<Map<String,Object>> queryWarnTypes();

    /**
     * 获取指标列表
     * @param
     * @return
     */
    List<Map<String, Object>> queryQuotaList(@Param("warntypes") String warntypes);

    /**
     * 获取保障类型
     * @return
     */
    List<Map<String,Object>> protectiontype();

    /**
     * 保存预警-预警清单合集
     * @return
     */
    int saveAdpWarningList(Map<String, Object> adpWarningListMap);

    /**
     * 删除预警-预警清单合集
     * @param id
     * @return
     */
    int delAdpWarningList(@Param("id") String id);

    /**
     * 根据预警分类获取预警指标
     * @param warnlo
     * @return
     */
    List<Map<String,Object>> getWarnByWarnlo(@Param("warnlo") String warnlo);

    /**
     * 唯一性的效验
     * @param warnBodyCode
     * @param warnStr
     * @return
     */
    int getBaseDateNameByCodeWarn(@Param("warnBodyCode") String warnBodyCode,@Param("warnStr") String warnStr);

    /**
     * 通过合同编号获取项目名称
     * @param warnBodyCode
     * @return
     */
    String getBaseDateNameById(@Param("warnBodyCode") String warnBodyCode);

    //采购-日报报缺日报-工厂列表
    List<Map<String,Object>>  selFactoryName();

    /*
     * 功能描述: <br>
     * @Param: 判断当前月份，此合同号有没有上传过视频
     * @Return: 
     * @Author: lihx
     * @Date: 2019/12/18 17:46
     */
    Map<String,Object> isVideo(@Param("map")Map paramMap);

    //替换为最新的视频文件信息
    void update(@Param("map")Map paramMap);

    //新增当前合同号，传入日期的视频信息
    void insert(@Param("map")Map paramMap);

}
