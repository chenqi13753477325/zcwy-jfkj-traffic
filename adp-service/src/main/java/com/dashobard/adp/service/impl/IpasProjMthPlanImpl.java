package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.IpasProjMthPlanService;
import com.dashboard.adp.dao.AdpCapacityMapper;
import com.dashboard.adp.dao.IpasProjMthPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 各阶段情况-供应资源
 *
 * @Author: c7
 * @Filename: IpasProjMthPlanImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/26 15:44
 * @Version: 1.0
 */
@Service(interfaceClass = IpasProjMthPlanService.class)
public class IpasProjMthPlanImpl implements IpasProjMthPlanService {

    @Autowired
    IpasProjMthPlanMapper ipasProjMthPlanMapper;

    @Autowired
    AdpCapacityMapper adpCapacityMapper;

    @Override
    public JSONArray list(String contractNo) {
        if(contractNo==null || "".equals(contractNo)){
            contractNo = "DQ-GJ-2018041";
        }
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> list = ipasProjMthPlanMapper.list(contractNo);
        for(int i=1;i<=31;i++){
            JSONObject jo = new JSONObject();
            jo.put("day",i);
            Integer k = 1;
            for(Map<String, Object> map : list){
                Integer o = (Integer)map.get("day" + i + "");
                jo.put("version"+k+"",o);
                k++;
            }
            for(int j=k;j<=12;j++){
                jo.put("version"+j+"","");
            }
            jsonArray.add(jo);
        }
        return jsonArray;
    }


    @Override
    public JSONArray adpPlanCarrieResourceLoad() {

        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> maps = ipasProjMthPlanMapper.adpPlanCarrieResourceLoad();
        for(Map<String, Object> map : maps){
            JSONObject jo = new JSONObject();
            jo.put("moth",(String)map.get("moth"));
            Integer mvPower = (Integer)map.get("mv_power");
            Integer qtCount = (Integer)map.get("qt_count");
            jo.put("qtCount",String.valueOf(qtCount));
            if((mvPower-qtCount) >= 0){
                //超出资源
                jo.put("overloading",String.valueOf(0));
                //剩余资源
                jo.put("surplusCapacity",String.valueOf((mvPower-qtCount)));
            }else{
                //超出资源
                jo.put("overloading",String.valueOf((qtCount-mvPower)));
                //剩余资源
                jo.put("surplusCapacity",String.valueOf(0));
            }
            jsonArray.add(jo);
        }

        return jsonArray;
    }


    @Override
    public JSONArray adpCapacityTb(String month) {
        JSONArray jsonArray = new JSONArray();

        Calendar cale = Calendar.getInstance();
        Integer monthInt = cale.get(Calendar.MONTH) + 1;
        String monthStr = "";
        if(month!=null && !"".equals(month)){
            monthStr = month;
        }else{
            monthStr = String.valueOf(monthInt);
        }
        JSONObject jo = new JSONObject();
        Long aLong = adpCapacityMapper.sumAdpCapacityFdj(monthStr);
        jo.put("bigPart","发电机");
        jo.put("capacity",aLong);
        JSONObject jo1 = new JSONObject();
        Long aLong1 = adpCapacityMapper.sumAdpCapacityJc(monthStr);
        jo1.put("bigPart","机舱");
        jo1.put("capacity",aLong1);
        JSONObject jo2 = new JSONObject();
        Long aLong2 = adpCapacityMapper.sumAdpCapacityYl(monthStr);
        jo2.put("bigPart","叶轮");
        jo2.put("capacity",aLong2);
        JSONObject jo3 = new JSONObject();
        Long aLong3 = adpCapacityMapper.sumAdpCapacityYp(monthStr);
        jo3.put("bigPart","叶片");
        jo3.put("capacity",aLong3);
        jsonArray.add(jo);
        jsonArray.add(jo1);
        jsonArray.add(jo2);
        jsonArray.add(jo3);
        return jsonArray;
    }


    @Override
    public JSONArray adpPlanProjectMap(String projectId) {

        if(projectId==null || "".equals(projectId)){
            projectId = "B00754";
        }
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> maps = ipasProjMthPlanMapper.adpPlanProjectMap(projectId);
        for(Map<String, Object> map : maps){
            Long zlCountInt = (Long)map.get("zl_count");
            JSONObject jo = new JSONObject();
            jo.put("bigParts","接货");
            Long jhCountInt = (Long)map.get("jh_count");
            jo.put("number",jhCountInt);
            jo.put("count",zlCountInt);
            JSONObject jo1 = new JSONObject();
            jo1.put("bigParts","吊装");
            Long dzCountInt = (Long)map.get("dz_count");
            jo1.put("number",dzCountInt);
            jo1.put("count",zlCountInt);
            JSONObject jo2 = new JSONObject();
            jo2.put("bigParts","调试");
            Long tsCountInt = (Long)map.get("ts_count");
            jo2.put("number",tsCountInt);
            jo2.put("count",zlCountInt);
            JSONObject jo3 = new JSONObject();
            jo3.put("bigParts","预验收");
            Long yysCountInt = (Long)map.get("yys_count");
            jo3.put("number",yysCountInt);
            jo3.put("count",zlCountInt);
            jsonArray.add(jo);
            jsonArray.add(jo1);
            jsonArray.add(jo2);
            jsonArray.add(jo3);
        }
        return jsonArray;
    }
}
