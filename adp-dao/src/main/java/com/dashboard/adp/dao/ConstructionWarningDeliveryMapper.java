package com.dashboard.adp.dao;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 预警告警-工程在建-接货预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningDeliveryMapper
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:43
 * @Version: 1.0
 */
public interface ConstructionWarningDeliveryMapper {

    /**
     * 接货预警项目总数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:11
     */
    Integer count();

    /**
     * 接货-首页-预警项目预警数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:11
     */
    Integer warningDeliveryCount();

    /**
     * 接货-首页-预警项目接货量
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:13
     */
    Integer sumArrivalNum();

    /**
     * 接货-首页-预警项目接货周期
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:15
     */
    Integer sumArrivalCycle();
    
    /**
     * 基础具备吊装条件到首个大部件到货时长-sum求和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 13:23
     */
    Integer sumFirstArrivalTime();

    /**
     * 到货未吊装时长-sum求和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 14:13
     */
    Integer sumArrivalNohoistingTime();

    /**
     * 主机到货未吊装数量-sum求和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 14:13
     */
    Integer sumHostArrivalNohoistingTime();

    /**
     * 单台塔筒平均生产周期-sum求和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 14:13
     */
    Integer sumSingleTowerCycel();

    /**
     * 塔架到货未吊装时长-sum求和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 14:13
     */
    Integer sumTowerArrivalNohoistingTime();
    
    /**
     * 基础具备吊装条件到首个大部件到货时长-大于10天
     * @param
     * @return 
     * @date   2018/12/6 15:26
     */
    Integer countFirstArrivalTimeGt();

    /**
     * 基础具备吊装条件到首个大部件到货时长-小于10天
     * @param
     * @return
     * @date   2018/12/6 15:26
     */
    Integer countFirstArrivalTimeLt();
    
    /**
     * 到货未吊装时长-大于30天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 15:39
     */
    Integer countArrivalNohoistingTimeGt();

    /**
     * 到货未吊装时长-小于30天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 15:39
     */
    Integer countArrivalNohoistingTimeLt();

    /**
     * 主机到货未吊装量-大于2
     * @param  
     * @return 
     * @date   2018/12/6 15:47
     */
    Integer countHostArrivalNohoistingTimeGt();
    
    /**
     * 主机到货未吊装量-小于2
     * @param
     * @return
     * @date   2018/12/6 15:47
     */
    Integer countHostArrivalNohoistingTimeLt();

    /**
     * 单台塔筒平均生产周期-大于65天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 15:51
     */
    Integer countsingleTowerCycelGt();
    
    /**
     * 单台塔筒平均生产周期-小于65天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 15:51
     */
    Integer countsingleTowerCycelLt();

    /**
     * 塔架到货未吊装量-大于2天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 16:01
     */
    Integer counttowerArrivalNohoistingTimeGt();

    /**
     * 塔架到货未吊装量-小于2天
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/6 16:01
     */
    Integer counttowerArrivalNohoistingTimeLt();
}
