package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.AdpCapacityService;
import com.dashboard.adp.dao.AdpCapacityMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各阶段-生产
 *
 * @Author: c7
 * @Filename: AdpCapacityImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/25 11:57
 * @Version: 1.0
 */
@Service(interfaceClass = AdpCapacityService.class)
public class AdpCapacityImpl implements AdpCapacityService {

    @Autowired
    AdpCapacityMapper adpCapacityMapper;


    @Override
    public JSONArray totalCapacity(String  dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                                   String factory, String largeparttype, String largepart) {
        JSONArray jsonArray = new JSONArray();
        Map<String,Object > sortMap = new HashMap<String,Object>();
        Calendar cale = Calendar.getInstance();
        Integer monthInt = cale.get(Calendar.MONTH) + 1;
        String startsMot = "";
        if(dateStart!=null && !"".equals(dateStart)){
            startsMot = dateStart.substring(5,7);
        }else{
            startsMot = String.valueOf(monthInt);
        }

        String endMot = "";
        if(dateEnd!=null && !"".equals(dateEnd)){
            endMot = dateEnd.substring(5,7);
        }else{
            endMot = startsMot;
        }
        int start = Integer.parseInt(startsMot);
        int end = Integer.parseInt(endMot);
        //发电价总装厂经纬度
        sortMap.put("month", start);
        sortMap.put("modelPlats", modelPlats);
        sortMap.put("machineType", machineType);
        sortMap.put("factorytype", factorytype);
        sortMap.put("factory", factory);
        sortMap.put("largeparttype", largeparttype);
        sortMap.put("largepart", largepart);
        JSONObject jo = new JSONObject();
        HashMap fdjTotal = adpCapacityMapper.fdjTotal(sortMap);
        HashMap jcTotal = adpCapacityMapper.jcTotal(sortMap);
        HashMap ylTotal = adpCapacityMapper.ylTotal(sortMap);
        HashMap ypTotal = adpCapacityMapper.ypTotal(sortMap);
        long fdj=0;
        long jc=0;
        long yp=0;
        long yl=0;
        for (int i=start; i <= end; i++) {
            fdj = fdj + (long)fdjTotal.get("sum" + i);
            jc = jc + (long)jcTotal.get("sum" + i);
            yl = yl + (long)ylTotal.get("sum" + i);
            yp = yp + (long)ypTotal.get("sum" + i);
        }
        jo.put("fdjTotal", fdj);
        jo.put("jcTotal", jc);
        jo.put("ylTotal", yl);
        jo.put("ypTotal", yp);
        jsonArray.add(jo);
        return jsonArray;
    }


    @Override
    public JSONArray mapCapacity(String dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                                 String factory, String largeparttype, String largepart) {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> sortMap = new HashMap<String, Object>();
        Calendar cale = Calendar.getInstance();
        Integer monthInt = cale.get(Calendar.MONTH) + 1;
        String startsMot = "";
        if (dateStart != null && !"".equals(dateStart)) {
            startsMot = dateStart.substring(5, 7);
        } else {
            startsMot = String.valueOf(monthInt);
        }

        String endMot = "";
        if (dateEnd != null && !"".equals(dateEnd)) {
            endMot = dateEnd.substring(5, 7);
        } else {
            endMot = startsMot;
        }
        int start = Integer.parseInt(startsMot);
        int end = Integer.parseInt(endMot);
        long fdj = 0;
        long jc = 0;
        long yl = 0;
        long yp = 0;

        //发电价总装厂经纬度
        sortMap.put("month", start);
        sortMap.put("modelPlats", modelPlats);
        sortMap.put("machineType", machineType);
        sortMap.put("factorytype", factorytype);
        sortMap.put("factory", factory);
        sortMap.put("largeparttype", largeparttype);
        sortMap.put("largepart", largepart);

        List<Map<String, Object>> fdjList = adpCapacityMapper.fdjMap(sortMap);
        if (fdjList != null && fdjList.size() > 0 ) {
            //判断是否进行时间筛选
            for (int k = 0; k < fdjList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name", (String) fdjList.get(k).get("factory"));
                jo.put("lng", (String) fdjList.get(k).get("factory_lng"));
                jo.put("lat", (String) fdjList.get(k).get("factory_lat"));
                for (int i=start; i <= end; i++) {
                    fdj = fdj + (Long) fdjList.get(k).get("sum" + i);
                }
                jo.put("capacity", "发电机产能:" + fdj);
                fdj=0;
                jsonArray.add(jo);
            }
        }
        //机舱总装厂经纬度
        List<Map<String, Object>> jcList = adpCapacityMapper.jcMap(sortMap);
        if (jcList != null && jcList.size() > 0) {
            //判断是否进行时间筛选
            for (int k = 0; k < jcList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name", (String) jcList.get(k).get("factory"));
                jo.put("lng", (String) jcList.get(k).get("factory_lng"));
                jo.put("lat", (String) jcList.get(k).get("factory_lat"));
                for (int i=start; i <= end; i++) {
                    jc = jc + (Long) jcList.get(k).get("sum" + i);
                }
                jo.put("capacity", "机舱产能:" + jc);
                jc=0;
                jsonArray.add(jo);
            }
        }

        //叶轮总装厂经纬度
        List<Map<String, Object>> ylList = adpCapacityMapper.ylMap(sortMap);
        if (ylList != null && ylList.size() > 0) {
            //判断是否进行时间筛选
            for (int k = 0; k < ylList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name", (String) ylList.get(k).get("factory"));
                jo.put("lng", (String) ylList.get(k).get("factory_lng"));
                jo.put("lat", (String) ylList.get(k).get("factory_lat"));
                for (int i = start; i <= end; i++) {
                    yl = yl + (Long) ylList.get(k).get("sum" + i);
                }
                jo.put("capacity", "叶轮产能:" + yl);
                yl=0;
                jsonArray.add(jo);
            }
        }

        //叶片供应商经纬度
        List<Map<String, Object>> ypList = adpCapacityMapper.ypMap(sortMap);
        if (ypList != null && ypList.size() > 0) {
            //判断是否进行时间筛选
            for (int k = 0; k < ypList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name", (String) ypList.get(k).get("supplier"));
                jo.put("lng", (String) ypList.get(k).get("factory_lng"));
                jo.put("lat", (String) ypList.get(k).get("factory_lat"));
                for (int i = start; i <= end; i++) {
                    yp = yp + (Long) ypList.get(k).get("sum" + i);
                }
                jo.put("capacity", "叶片产能:" + yp);
                yp=0;
                jsonArray.add(jo);
            }
        }
        return jsonArray;
    }

    @Override
    public JSONArray listCapacity(String  dateStart, String dateEnd, String modelPlats, String machineType, String factorytype,
                                  String factory, String largeparttype, String largepart) {
        JSONArray jsonArray = new JSONArray();
        Map<String,Object > sortMap = new HashMap<String,Object>();
        Calendar cale = Calendar.getInstance();
        Integer monthInt = cale.get(Calendar.MONTH) + 1;
        String startsMot = "";
        if(dateStart!=null && !"".equals(dateStart)){
            startsMot = dateStart.substring(5,7);
        }else{
            startsMot = String.valueOf(monthInt);
        }

        String endMot = "";
        if(dateEnd!=null && !"".equals(dateEnd)){
            endMot = dateEnd.substring(5,7);
        }else{
            //endMot = String.valueOf(dateEnd);
            //dataEnd变量为“”时，上面代码出问题，逻辑不通 jiangxg 2019-03-22
            endMot = String.valueOf(monthInt);
        }
        int start = Integer.parseInt(startsMot);
        int end = Integer.parseInt(endMot);
        long fdj = 0;
        long jc = 0;
        long yl = 0;
        long yp = 0;
        sortMap.put("month",start);
        sortMap.put("modelPlats",modelPlats);
        sortMap.put("machineType",machineType);
        sortMap.put("factorytype",factorytype);
        sortMap.put("factory",factory);
        sortMap.put("largeparttype",largeparttype);
        sortMap.put("largepart",largepart);
        //叶片列表
        List<Map<String, Object>> ypList = adpCapacityMapper.ypMap(sortMap);
        if(ypList!=null && ypList.size() > 0){
            for (int k = 0; k < ypList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name",(String)ypList.get(k).get("supplier"));
                jo.put("daBuJian","叶片");
                String model = (String) ypList.get(k).get("model");
                if(model!=null && !"".equals(model)){
                    jo.put("model",model);
                }else{
                    jo.put("model","");
                }
                for (int i = start; i <= end; i++) {
                    yp = yp + (Long) ypList.get(k).get("sum" + i);
                }
                jo.put("capacity",yp);
                jsonArray.add(jo);
            }
        }

        //机舱列表
        List<Map<String, Object>> jcList = adpCapacityMapper.jcMap(sortMap);
        if(jcList!=null && jcList.size() > 0 ){
            for (int k = 0; k < jcList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name",(String)jcList.get(k).get("factory"));
                jo.put("daBuJian","机舱");
                String model = (String) jcList.get(k).get("model");
                if(model!=null && !"".equals(model)){
                    jo.put("model",model);
                }else{
                    jo.put("model","");
                }
                for (int i = start; i <= end; i++) {
                    jc = jc + (Long) jcList.get(k).get("sum" + i);
                }
                jo.put("capacity",jc);
                jsonArray.add(jo);
            }
        }

        //叶轮列表
        List<Map<String, Object>> ylList = adpCapacityMapper.ylMap(sortMap);
        if(ylList!=null && ylList.size() > 0){
            for (int k = 0; k < ylList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name",(String)ylList.get(k).get("factory"));
                jo.put("daBuJian","叶轮");
                String model = (String) ylList.get(k).get("model");
                if(model!=null && !"".equals(model)){
                    jo.put("model",model);
                }else{
                    jo.put("model","");
                }
                for (int i = start; i <= end; i++) {
                    yl = yl + (Long) ylList.get(k).get("sum" + i);
                }
                jo.put("capacity",yl);
                jsonArray.add(jo);
            }
        }



        //发电机列表
        List<Map<String, Object>> fdjList = adpCapacityMapper.fdjMap(sortMap);
        if(fdjList != null && fdjList.size() > 0){
            for (int k = 0; k < fdjList.size(); k++) {
                JSONObject jo = new JSONObject();
                jo.put("name",(String)fdjList.get(k).get("factory"));
                jo.put("daBuJian","发电机");
                String model = (String) fdjList.get(k).get("model");
                if(model!=null && !"".equals(model)){
                    jo.put("model",model);
                }else{
                    jo.put("model","");
                }
                for (int i = start; i <= end; i++) {
                    fdj = fdj + (Long) fdjList.get(k).get("sum" + i);
                }
                jo.put("capacity",fdj);
                jsonArray.add(jo);
            }
        }

        return jsonArray;
    }
}
