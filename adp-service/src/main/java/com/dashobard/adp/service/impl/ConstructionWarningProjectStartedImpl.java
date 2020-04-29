package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.ConstructionWarningProjectStartedService;
import com.dashboard.adp.dao.ConstructionWarningDeliveryMapper;
import com.dashboard.adp.dao.ConstructionWarningHositingMapper;
import com.dashboard.adp.dao.ConstructionWarningProjectStartedMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;


/**
 *
 * @Filename: ConstructionWarningProjectStartedImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/11/26 16:09
 * @Version: 1.0
 */
@Service(interfaceClass = ConstructionWarningProjectStartedService.class)
public class ConstructionWarningProjectStartedImpl implements ConstructionWarningProjectStartedService {

    @Autowired
    ConstructionWarningProjectStartedMapper constructionWarningProjectStartedMapper;

    @Autowired
    ConstructionWarningDeliveryMapper constructionWarningDeliveryMapper;

    @Autowired
    ConstructionWarningHositingMapper constructionWarningHositingMapper;

    @Override
    public JSONArray cwpsCount() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        //开工总条数
        Integer count = constructionWarningProjectStartedMapper.cwpsCount();
        //计划开工预警数
        Integer cwpsPlanStartCount = constructionWarningProjectStartedMapper.cwpsPlanStartCount();
        //项目计划开工率
        Integer ratio = constructionWarningProjectStartedMapper.cwpsPlanStartRatio();
        double i = (double)ratio/100;

        //项目计划开工率
        jo.put("cwpsPlanStartCountRatio",nf.format(i/count));
        //开工预警数
        jo.put("cwpsPlanStartCount",cwpsPlanStartCount);
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray countAndRatio(String startWorkType) {
        JSONArray jsonArray = new JSONArray();
        //开工总条数
        Integer count = constructionWarningProjectStartedMapper.cwpsCount();
        //接货总条数
        Integer deliveryCount = constructionWarningDeliveryMapper.count();
        //吊装总条数
        Integer hositingCount = constructionWarningHositingMapper.count();

        //三证完成率
        if(startWorkType!=null && startWorkType.equals("threeEvidence")){
            for(int threeEvidence=0;threeEvidence<4;threeEvidence++){
                JSONObject jo = new JSONObject();
                jo.put("title",""+threeEvidence+"/3");
                //三证完成数量
                Integer threeEvidenceCount = constructionWarningProjectStartedMapper.threeEvidenceCount(threeEvidence);
                jo.put("ratio",threeEvidenceCount);
                //总数
                jo.put("count",count);
                jsonArray.add(jo);
            }
        }

        //四标定标率
        if(startWorkType!=null && startWorkType.equals("calibration")){
            for(int calibration=0;calibration<5;calibration++){
                JSONObject jo = new JSONObject();
                jo.put("title",""+calibration+"/4");
                //四标定标率数量
                Integer calibrationCount = constructionWarningProjectStartedMapper.calibrationCount(calibration);
                jo.put("ratio",calibrationCount);
                //总数
                jo.put("count",count);
                jsonArray.add(jo);
            }
        }

        //四标进场率数量
        if(startWorkType!=null && startWorkType.equals("fourStandard")){
            for(int fourStandard=0;fourStandard<5;fourStandard++){
                JSONObject jo = new JSONObject();
                jo.put("title",""+fourStandard+"/4");
                //四标进场率数量
                Integer fourStandardCount = constructionWarningProjectStartedMapper.fourStandardCount(fourStandard);
                jo.put("ratio",fourStandardCount);
                //总数
                jo.put("count",count);
                jsonArray.add(jo);
            }
        }

        //基础施工开工率
        if(startWorkType!=null && startWorkType.equals("baseCommRate")){
            for(int i = 0; i<2;i++){
                JSONObject jo = new JSONObject();
                if(i==0){
                    jo.put("title","超过80%");
                    Integer integer = constructionWarningProjectStartedMapper.countBaseCommRateRatioGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",count);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未超过80%");
                    Integer integer = constructionWarningProjectStartedMapper.countBaseCommRateRatioIt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",count);
                    jsonArray.add(jo);
                }
            }
        }


        //机位齐套率
        if(startWorkType!=null && startWorkType.equals("completeRate")){
            for(int i = 0; i<2;i++){
                JSONObject jo = new JSONObject();
                if(i==0){
                    jo.put("title","超过40%");
                    Integer integer = constructionWarningProjectStartedMapper.countCompleteRateRatioGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",count);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未超过40%");
                    Integer integer = constructionWarningProjectStartedMapper.countCompleteRateRatioIt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",count);
                    jsonArray.add(jo);
                }
            }
        }

        //基础具备吊装条件到首个大部件到货时长
        if(startWorkType!=null && startWorkType.equals("firstArrivalTime")){
            for (int i=0;i<2;i++){
             JSONObject jo = new JSONObject();
             if(i == 0){
                 jo.put("title","大10天");
                 Integer integer = constructionWarningDeliveryMapper.countFirstArrivalTimeGt();
                 jo.put("ratio",integer);
                 //总数
                 jo.put("count",deliveryCount);
                 jsonArray.add(jo);
             }else{
                 jo.put("title","小10天");
                 Integer integer = constructionWarningDeliveryMapper.countFirstArrivalTimeLt();
                 jo.put("ratio",integer);
                 jo.put("count",deliveryCount);
                 jsonArray.add(jo);
             }
            }
        }

        //到货未吊装时长
        if(startWorkType!=null && startWorkType.equals("arrivalNohoistingTime")){
            for (int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","大30天");
                    Integer integer = constructionWarningDeliveryMapper.countArrivalNohoistingTimeGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","小30天");
                    Integer integer = constructionWarningDeliveryMapper.countArrivalNohoistingTimeLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }
            }
        }

        //主机到货未吊装量
        if(startWorkType!=null && startWorkType.equals("hostArrivalNohoistingTime")){
            for (int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","大2个");
                    Integer integer = constructionWarningDeliveryMapper.countHostArrivalNohoistingTimeGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","小2个");
                    Integer integer = constructionWarningDeliveryMapper.countHostArrivalNohoistingTimeLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }
            }
        }

        //单台塔筒平均生产周期
        if(startWorkType!=null && startWorkType.equals("singleTowerCycel")){
            for (int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","大65天");
                    Integer integer = constructionWarningDeliveryMapper.countsingleTowerCycelGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","小65天");
                    Integer integer = constructionWarningDeliveryMapper.countsingleTowerCycelLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }
            }
        }

        //塔架到货未吊装量
        if(startWorkType!=null && startWorkType.equals("towerArrivalNohoistingTime")){
            for (int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","大2个");
                    Integer integer = constructionWarningDeliveryMapper.counttowerArrivalNohoistingTimeGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","小2个");
                    Integer integer = constructionWarningDeliveryMapper.counttowerArrivalNohoistingTimeLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",deliveryCount);
                    jsonArray.add(jo);
                }
            }
        }

        //项目计划开吊率
        if(startWorkType!=null && startWorkType.equals("pjPlanHositingRate")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.pjPlanHositingRateLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.pjPlanHositingRateGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        //首台齐套到货到首吊时长
        if(startWorkType!=null && startWorkType.equals("firstArrivedCompleteTime")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.firstArrivedCompleteTimeGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.firstArrivedCompleteTimeLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        //首台吊装预备齐套数
        if(startWorkType!=null && startWorkType.equals("firstHositingPrepareNumber")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.firstHositingPrepareNumberGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.firstHositingPrepareNumberLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        //主吊进场后准备时长
        if(startWorkType!=null && startWorkType.equals("mainCranePrepareTime")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.mainCranePrepareTimeGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.mainCranePrepareTimeLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        //单台主吊作业效率
        if(startWorkType!=null && startWorkType.equals("singleCraneRate")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.singleCraneRateLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.singleCraneRateGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        /**
         * 吊车转场效率
         * @param  [startWorkType]
         * @return com.alibaba.fastjson.JSONArray
         * @date   2018/12/10 9:36
         */
        if(startWorkType!=null && startWorkType.equals("craneTransfer")){
            for(int i=0;i<2;i++){
                JSONObject jo = new JSONObject();
                if(i == 0){
                    jo.put("title","预警");
                    Integer integer = constructionWarningHositingMapper.craneTransferGt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }else{
                    jo.put("title","未预警");
                    Integer integer = constructionWarningHositingMapper.craneTransferLt();
                    jo.put("ratio",integer);
                    //总数
                    jo.put("count",hositingCount);
                    jsonArray.add(jo);
                }
            }
        }

        return jsonArray;
    }


    @Override
    public JSONArray threeEvidenceRatio() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        Double j = 0.00;
        Double k = 0.00;
        for(int i=0;i<4;i++){
            //三证完成率，每个等级完成的数量
            Integer threeEvidenceCount = constructionWarningProjectStartedMapper.threeEvidenceCount(i);
            k = k + threeEvidenceCount;
            if(i==0){
                double v = threeEvidenceCount * 0.00;
                j = j + v;
            }else if(i==1){
                double v = threeEvidenceCount * 0.33;
                j = j + v;
            }else if(i==2){
                double v = threeEvidenceCount * 0.66;
                j = j + v;
            }else if(i==3){
                double v = threeEvidenceCount * 0.99;
                j = j + v;
            }
        }
        jo.put("threeEvidenceRatio",nf.format(j/k));
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray calibrationRatio() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        Double j = 0.00;
        Double k = 0.00;
        for(int i=0;i<5;i++){
            //四标定标率，每个等级完成的数量
            Integer calibrationCount = constructionWarningProjectStartedMapper.calibrationCount(i);
            k = k + calibrationCount;
            if(i==0){
                double v = calibrationCount * 0.00;
                j = j + v;
            }else if(i==1){
                double v = calibrationCount * 0.25;
                j = j + v;
            }else if(i==2){
                double v = calibrationCount * 0.50;
                j = j + v;
            }else if(i==3){
                double v = calibrationCount * 0.75;
                j = j + v;
            }else if(i==4){
                double v = calibrationCount * 0.99;
                j = j + v;
            }
        }
        jo.put("calibrationRatio",nf.format(j/k));
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray fourStandardRatio() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        Double j = 0.00;
        Double k = 0.00;
        for(int i=0;i<5;i++){
            //四标进场率，每个等级完成的数量
            Integer fourStandardCount = constructionWarningProjectStartedMapper.fourStandardCount(i);
            k = k + fourStandardCount;
            if(i==0){
                double v = fourStandardCount * 0.00;
                j = j + v;
            }else if(i==1){
                double v = fourStandardCount * 0.25;
                j = j + v;
            }else if(i==2){
                double v = fourStandardCount * 0.50;
                j = j + v;
            }else if(i==3){
                double v = fourStandardCount * 0.75;
                j = j + v;
            }else if(i==4){
                double v = fourStandardCount * 0.99;
                j = j + v;
            }
        }
        jo.put("fourStandardRatio",nf.format(j/k));
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray baseCommRateRatio() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        //项目基础开工率的总和
        Integer sum = constructionWarningProjectStartedMapper.sumBaseCommRateRatio();
        //开工总条数
        Integer count = constructionWarningProjectStartedMapper.cwpsCount();
        double i = (double)sum/100;
        jo.put("baseCommRateRatio",nf.format(i/count));
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray completeRateRatio() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        NumberFormat nf = NumberFormat.getPercentInstance();
        //机位齐套率的总和
        Integer sum = constructionWarningProjectStartedMapper.sumCompleteRateRatio();
        //开工总条数
        Integer count = constructionWarningProjectStartedMapper.cwpsCount();
        double i = (double)sum/100;
        jo.put("completeRateRatio",nf.format(i/count));
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray threeEvidenceProject(Integer threeEvidence) {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> threeEvidenceProjectList = constructionWarningProjectStartedMapper.threeEvidenceProject(threeEvidence);
        if(threeEvidenceProjectList!=null){
            Integer a = 1;
            for(Map<String, Object> map: threeEvidenceProjectList){
                JSONObject jo = new JSONObject();
                jo.put("pjId",a);
                for(String k : map.keySet()){
                    jo.put("pjName",map.get(k));
                }
                if(threeEvidence == 0){
                    jo.put("earlyWarningIndex","三证完成率0%");
                }else if(threeEvidence == 1){
                    jo.put("earlyWarningIndex","三证完成率33%");
                }else if(threeEvidence == 2){
                    jo.put("earlyWarningIndex","三证完成率66%");
                }else if(threeEvidence == 3){
                    jo.put("earlyWarningIndex","三证完成率99%");
                }
                jsonArray.add(jo);
                a++;
            }
        }
        return jsonArray;
    }


    @Override
    public JSONArray calibrationProject(Integer calibration) {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> calibrationProjectList = constructionWarningProjectStartedMapper.calibrationProject(calibration);
        if(calibrationProjectList!=null){
            for(Map<String, Object> map: calibrationProjectList){
                JSONObject jo = new JSONObject();
                for(String k : map.keySet()){
                    jo.put("pjName",map.get(k));
                }
                if(calibration == 0){
                    jo.put("earlyWarningIndex","四标定标率0%");
                }else if(calibration == 1){
                    jo.put("earlyWarningIndex","四标定标率25%");
                }else if(calibration == 2){
                    jo.put("earlyWarningIndex","四标定标率50%");
                }else if(calibration == 3){
                    jo.put("earlyWarningIndex","四标定标率75%");
                }else if(calibration == 4){
                    jo.put("earlyWarningIndex","四标定标率99%");
                }
                jsonArray.add(jo);
            }
        }
        return jsonArray;
    }


    @Override
    public JSONArray fourStandardProject(Integer fourStandard) {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> fourStandardProjectList = constructionWarningProjectStartedMapper.fourStandardProject(fourStandard);
        if(fourStandardProjectList!=null){
            for(Map<String, Object> map: fourStandardProjectList){
                JSONObject jo = new JSONObject();
                for(String k : map.keySet()){
                    jo.put("pjName",map.get(k));
                }
                if(fourStandard == 0){
                    jo.put("earlyWarningIndex","四标进场率0%");
                }else if(fourStandard == 1){
                    jo.put("earlyWarningIndex","四标进场率33%");
                }else if(fourStandard == 2){
                    jo.put("earlyWarningIndex","四标进场率66%");
                }else if(fourStandard == 3){
                    jo.put("earlyWarningIndex","四标进场率99%");
                }
                jsonArray.add(jo);
            }
        }
        return jsonArray;
    }



    @Override
    public JSONArray baseCommrateProject(Integer baseCmmrate) {
        return null;
    }
}
