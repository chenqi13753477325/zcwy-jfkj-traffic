package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.ConstructionWarningDeliveryService;
import com.dashboard.adp.api.service.ConstructionWarningHositingService;
import com.dashboard.adp.dao.ConstructionWarningDeliveryMapper;
import com.dashboard.adp.dao.ConstructionWarningHositingMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 预警告警-工程在建-吊装预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningHositingImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:39
 * @Version: 1.0
 */
@Service(interfaceClass = ConstructionWarningHositingService.class)
public class ConstructionWarningHositingImpl implements ConstructionWarningHositingService {


    @Autowired
    ConstructionWarningHositingMapper constructionWarningHositingMapper;

    @Override
    public JSONArray warningHositing() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //预警数
        Integer warningHositingCount = constructionWarningHositingMapper.warningHositingCount();
        jo.put("warningHositingCount",warningHositingCount);
        //新增装机量
        Integer sumAddHositingNum = constructionWarningHositingMapper.sumAddHositingNum();
        int i = sumAddHositingNum / count;
        jo.put("addHositingNum",String.valueOf(i)+"KWh");
        //吊装周期
        Integer sumHositingCycle = constructionWarningHositingMapper.sumHositingCycle();
        int i1 = sumHositingCycle / count;
        jo.put("hositingCycle",String.valueOf(i1)+"KWh");
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray pjPlanHositingRate() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //项目计划开吊率
        Integer sumSingleTowerCycel = constructionWarningHositingMapper.sumPjPlanHositingRate();
        if(sumSingleTowerCycel%count == 0){
            int i = sumSingleTowerCycel / count;
            jo.put("pjPlanHositingRate",String.valueOf(i)+"%");
        }else{
            double v1 = (double) sumSingleTowerCycel/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("pjPlanHositingRate",String.valueOf(v)+"%");
        }
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray mainCraneNumber() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //主吊数量-sum求和
        Integer sumMainCraneNumber = constructionWarningHositingMapper.sumMainCraneNumber();
        int i = sumMainCraneNumber / count;
        jo.put("mainCraneNumber",String.valueOf(i)+"个");
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray firstArrivedCompleteTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //首台齐套到货到首吊时长-sum求和
        Integer sumFirstArrivedCompleteTime = constructionWarningHositingMapper.sumFirstArrivedCompleteTime();
        if(sumFirstArrivedCompleteTime%count == 0){
            int i = sumFirstArrivedCompleteTime/count;
            jo.put("firstArrivedCompleteTime",String.valueOf(i)+"天");
        }else {
            double v1 = (double)sumFirstArrivedCompleteTime/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("firstArrivedCompleteTime",String.valueOf(v)+"天");
        }
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray firstHositingPrepareNumber() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //首台吊装预备齐套数-sum求和
        Integer sumFirstHositingPrepareNumber = constructionWarningHositingMapper.sumFirstHositingPrepareNumber();
        if(sumFirstHositingPrepareNumber%count == 0){
            int i = sumFirstHositingPrepareNumber/count;
            jo.put("firstHositingPrepareNumber",String.valueOf(i)+"套");
        }else {
            double v1 = (double)sumFirstHositingPrepareNumber/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("firstHositingPrepareNumber",String.valueOf(v)+"套");
        }

        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray mainCranePrepareTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //主吊进场后准备时长-sum求和
        Integer sumMainCranePrepareTime = constructionWarningHositingMapper.sumMainCranePrepareTime();
        if(sumMainCranePrepareTime%count == 0){
            int i = sumMainCranePrepareTime/count;
            jo.put("mainCranePrepareTime",String.valueOf(i)+"%");
        }else{
            double v1 = (double)sumMainCranePrepareTime/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("mainCranePrepareTime",String.valueOf(v)+"%");
        }
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray singleCraneRate() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //单台主吊作业效率-sum求和
        Integer sumSingleCraneRate = constructionWarningHositingMapper.sumSingleCraneRate();
        if(sumSingleCraneRate%count == 0){
            int i = sumSingleCraneRate/count;
            jo.put("singleCraneRate",String.valueOf(i)+"%");
        }else{
            double v1 = (double)sumSingleCraneRate/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("singleCraneRate",String.valueOf(v)+"%");
        }
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray craneTransfer() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //吊车转场效率-sum求和
        Integer sumCraneTransfer = constructionWarningHositingMapper.sumCraneTransfer();
        if(sumCraneTransfer%count == 0){
            int i = sumCraneTransfer/count;
            jo.put("craneTransfer",String.valueOf(i)+"%");
        }else{
            double v1 = (double)sumCraneTransfer/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("craneTransfer",String.valueOf(v)+"%");
        }
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray hositingNodebugNumber() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总数
        Integer count = constructionWarningHositingMapper.count();
        //吊装未调试量
        Integer sumHositingNodebugNumber = constructionWarningHositingMapper.sumHositingNodebugNumber();
        if(sumHositingNodebugNumber%count == 0){
            int i = sumHositingNodebugNumber/count;
            jo.put("hositingNodebugNumber",String.valueOf(i)+"KWn");
        }else {
            double v1 = (double)sumHositingNodebugNumber/count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("hositingNodebugNumber",String.valueOf(v)+"KWn");
        }
        jsonArray.add(jo);
        return jsonArray;
    }
}
