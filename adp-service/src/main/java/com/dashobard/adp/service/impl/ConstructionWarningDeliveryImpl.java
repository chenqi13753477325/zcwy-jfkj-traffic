package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.ConstructionWarningDeliveryService;
import com.dashboard.adp.dao.ConstructionWarningDeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 预警告警-工程在建-接货预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningDeliveryImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:39
 * @Version: 1.0
 */
@Service(interfaceClass = ConstructionWarningDeliveryService.class)
public class ConstructionWarningDeliveryImpl implements ConstructionWarningDeliveryService {


    @Autowired
    ConstructionWarningDeliveryMapper constructionWarningDeliveryMapper;

    @Override
    public JSONArray warningDelivery() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //预警数
        Integer warningDeliveryCount = constructionWarningDeliveryMapper.warningDeliveryCount();
        jo.put("warningDeliveryCount",warningDeliveryCount);
        //接货量-总和，求平均数
        Integer arrivalNumSum = constructionWarningDeliveryMapper.sumArrivalNum();
        int i = arrivalNumSum / count;
        jo.put("arrivalNumSum",String.valueOf(i)+"万KWh");

        //接货周天-总和，求平均数
        Integer arrivalCycleSum = constructionWarningDeliveryMapper.sumArrivalCycle();
        int i1 = arrivalCycleSum / count;
        jo.put("arrivalCycleSum",String.valueOf(i1)+"天");
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray firstArrivalTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //基础具备吊装条件到首个大部件到货时长-sum求和
        Integer sumFirstArrivalTime = constructionWarningDeliveryMapper.sumFirstArrivalTime();
        if(sumFirstArrivalTime%count == 0){
            int i = sumFirstArrivalTime / count;
            jo.put("firstArrivalTime",String.valueOf(i)+"天");
        }else{
            double v1 = (double) sumFirstArrivalTime / count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("firstArrivalTime",String.valueOf(v)+"天");
        }
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray arrivalNohoistingTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //到货未吊装时长-sum求和
        Integer sumArrivalNohoistingTime = constructionWarningDeliveryMapper.sumArrivalNohoistingTime();
        if(sumArrivalNohoistingTime%count == 0){
            int i = sumArrivalNohoistingTime/count;
            jo.put("arrivalNohoistingTime",String.valueOf(i)+"天");
        }else{
            double v1 = (double) sumArrivalNohoistingTime / count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("arrivalNohoistingTime",String.valueOf(v)+"天");
        }

        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray hostArrivalNohoistingTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //主机到货未吊装数量-sum求和
        Integer sumHostArrivalNohoistingTime = constructionWarningDeliveryMapper.sumHostArrivalNohoistingTime();
        jo.put("hostArrivalNohoistingTime",String.valueOf(sumHostArrivalNohoistingTime/count)+"个");
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray singleTowerCycel() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //单台塔筒平均生产周期
        Integer sumSingleTowerCycel = constructionWarningDeliveryMapper.sumSingleTowerCycel();
        if(sumSingleTowerCycel%count == 0){
            int i = sumSingleTowerCycel/count;
            jo.put("singleTowerCycel",String.valueOf(i)+"天");
        }else{
            double v1 = (double) sumSingleTowerCycel / count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("singleTowerCycel",String.valueOf(v)+"天");
        }
        jsonArray.add(jo);
        return jsonArray;
    }

    @Override
    public JSONArray towerArrivalNohoistingTime() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        //总项目数
        Integer count = constructionWarningDeliveryMapper.count();
        //塔架到货未吊装时长
        Integer sumTowerArrivalNohoistingTime = constructionWarningDeliveryMapper.sumTowerArrivalNohoistingTime();
        if(sumTowerArrivalNohoistingTime%count == 0){
            int i = sumTowerArrivalNohoistingTime/count;
            jo.put("towerArrivalNohoistingTime",String.valueOf(i)+"天");
        }else{
            double v1 = (double) sumTowerArrivalNohoistingTime / count;
            BigDecimal b = new BigDecimal(v1);
            double v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            jo.put("towerArrivalNohoistingTime",String.valueOf(v)+"天");
        }
        jsonArray.add(jo);
        return jsonArray;
    }
}
