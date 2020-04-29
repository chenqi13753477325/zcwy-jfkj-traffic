package com.dashboard.adp.dao;


import java.util.List;
import java.util.Map;

/**
 * 工程在建-项目开工预警
 *
 * @Filename: ConstructionWarningProjectStartedDao
 * @Email: chenq@icst.com.cn
 * @Date: 2018/11/29 11:32
 * @Version: 1.0
 */
public interface ConstructionWarningProjectStartedMapper {

    /**
     * 获取总开工条数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/11/29 11:36
     */
    Integer cwpsCount();

    /**
     * 获取开工预警数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/11/29 13:29
     */
    Integer cwpsPlanStartCount();

    /**
     * 项目计划开工率
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 17:18
     */
    Integer cwpsPlanStartRatio();


    /**
     * 三证完成率
     * @param  [threeEvidence]
     * @return java.lang.Integer
     * @date   2018/11/29 16:46
     */
    Integer threeEvidenceCount(Integer threeEvidence);


    /**
     * 三证完成关联的项目
     * @param  [threeEvidence]
     * @return java.util.List<java.lang.Object>
     * @date   2018/11/29 17:12
     */
    List<Map<String,Object>>  threeEvidenceProject(Integer threeEvidence);


    /**
     * 四标定标率
     * @param  [calibration]
     * @return java.lang.Integer
     * @date   2018/11/29 18:41
     */
    Integer calibrationCount(Integer calibration);

    /**
     * 四标定标率关联的项目
     * @param  [calibration]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/11/29 18:41
     */
    List<Map<String,Object>> calibrationProject(Integer calibration);

    /**
     * 四标进场率
     * @param  [fourStandard]
     * @return java.lang.Integer
     * @date   2018/11/29 18:55
     */
    Integer fourStandardCount(Integer fourStandard);


    /**
     * 四标进场率关联的项目
     * @param  [fourStandard]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date   2018/11/29 18:55
     */
    List<Map<String,Object>> fourStandardProject(Integer fourStandard);


    /**
     * 项目基础开工率的预警总和
     * @param  []
     * @return 
     * @date   2018/12/5 16:38
     */
    Integer sumBaseCommRateRatio();

    /**
     * 项目基础开工率的预警数
     * @param  []
     * @return
     * @date   2018/12/5 16:38
     */
    Integer countBaseCommRateRatioIt();
    Integer countBaseCommRateRatioGt();


    /**
     * 机位齐套率的预警总和
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 16:49
     */
    Integer sumCompleteRateRatio();

    /**
     * 机位齐套率的预警数
     * @param  []
     * @return java.lang.Integer
     * @date   2018/12/5 16:55
     */
    Integer countCompleteRateRatioIt();
    Integer countCompleteRateRatioGt();


}
