package com.dashboard.adp.dao;

/**
 * 预警告警-工程在建-吊装预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningHositingMapper
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:43
 * @Version: 1.0
 */
public interface ConstructionWarningHositingMapper {

    /**
     * 吊装项目总条数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:52
     */
    Integer count();

    /**
     * 吊装-首页-预警数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 19:53
     */
    Integer warningHositingCount();

    /**
     * 吊装-首页-新增装机量-sum总数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 16:04
     */
    Integer sumAddHositingNum();

    /**
     * 吊装-首页-吊装周期-sum总数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 16:13
     */
    Integer sumHositingCycle();

    /**
     * 吊装-明细-项目计划开吊率-sum总和
     * @param
     * @return java.lang.Integer
     * @date   2018/12/7 16:28
     */
    Integer sumPjPlanHositingRate();

    /**
     * 吊装-明细-项目计划开吊率-未预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:16
     */
    Integer pjPlanHositingRateGt();

    /**
     * 吊装-明细-项目计划开吊率-预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:16
     */
    Integer pjPlanHositingRateLt();

    /**
     * 吊装-明细-主吊数量-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 16:42
     */
    Integer sumMainCraneNumber();

    /**
     * 吊装-明细-首台齐套到货到首吊时长-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 17:01
     */
    Integer sumFirstArrivedCompleteTime();

    /**
     * 吊装-明细-首台齐套到货到首吊时长-未预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:24
     */
    Integer firstArrivedCompleteTimeLt();

    /**
     * 吊装-明细-首台齐套到货到首吊时长-预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:24
     */
    Integer firstArrivedCompleteTimeGt();

    /**
     * 吊装-明细-首台吊装预备齐套数-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 17:14
     */
    Integer sumFirstHositingPrepareNumber();

    /**
     * 吊装-明细-首台吊装预备齐套数-未预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:28
     */
    Integer firstHositingPrepareNumberLt();

    /**
     * 吊装-明细-首台吊装预备齐套数-预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:28
     */
    Integer firstHositingPrepareNumberGt();

    /**
     * 吊装-明细-主吊进场后准备时长-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 17:43
     */
    Integer sumMainCranePrepareTime();

    /**
     * 吊装-明细-主吊进场后准备时长-预警
     * @param  []
     * @return
     * @date   2018/12/10 9:31
     */
    Integer mainCranePrepareTimeGt();

    /**
     * 吊装-明细-主吊进场后准备时长-未预警
     * @param  []
     * @return
     * @date   2018/12/10 9:31
     */
    Integer mainCranePrepareTimeLt();
    


    /**
     * 吊装-明细-单台主吊作业效率-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 17:54
     */
    Integer sumSingleCraneRate();

    /**
     * 吊装-明细-单台主吊作业效率-预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:34
     */
    Integer singleCraneRateLt();

    /**
     * 吊装-明细-单台主吊作业效率-未预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:34
     */
    Integer singleCraneRateGt();


    /**
     * 吊装-明细-吊车转场效率-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 18:02
     */
    Integer sumCraneTransfer();

    /**
     * 吊装-明细-吊车转场效率-未预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:38
     */
    Integer craneTransferLt();

    /**
     * 吊装-明细-吊车转场效率-预警
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/10 9:38
     */
    Integer craneTransferGt();

    /**
     * 吊装-明细-吊装未调试量-sum总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/7 18:12
     */
    Integer sumHositingNodebugNumber();


}
