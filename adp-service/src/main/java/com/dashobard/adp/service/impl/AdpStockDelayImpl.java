package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.service.AdpStockDelayService;
import com.dashboard.adp.dao.AdpStockDelayMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各阶段情况-库存
 *
 * @Author: c7
 * @Filename: AdpStockDelayImpl
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/11 19:37
 * @Version: 1.0
 */
@Service(interfaceClass = AdpStockDelayService.class)
public class AdpStockDelayImpl implements AdpStockDelayService {

    @Autowired
    AdpStockDelayMapper adpStockDelayMapper;

    @Override
    public JSONArray adpStockDelayList() {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> adpStockDelayList = adpStockDelayMapper.adpStockDelayList();
        if(adpStockDelayList !=null && adpStockDelayList.size() > 0){
            for(Map<String, Object> map : adpStockDelayList){
                JSONObject jo = new JSONObject();
                Integer stockAgeGt90Int = (Integer)map.get("stock_age_gt90");
                if(stockAgeGt90Int > 0){
                    jo.put("warning","1");
                    jo.put("stockAgeGt90","超期90天");
                }else{
                    jo.put("warning","2");
                    jo.put("stockAgeGt90","超期60天");
                }
                jo.put("pjName",(String)map.get("pj_name"));
                jo.put("factoryName",(String)map.get("factory_name"));
                jo.put("matrielName",(String)map.get("matriel_name"));
                jsonArray.add(jo);
            }
        }
        return jsonArray;
    }


    @Override
    public JSONArray adpStorageLoad() {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> adpStorageLoadMap = adpStockDelayMapper.adpStorageLoad();
        for(Map<String, Object> map : adpStorageLoadMap){
            JSONObject jo = new JSONObject();
            //总装厂名称
            jo.put("factoryName",(String)map.get("factory_name"));
            //库容
            Integer storageCapacity = (Integer)map.get("storage_capacity");
            //已占库容
            Long used = (Long)map.get("used");
            if((storageCapacity-used)>=0){
                //超负荷
                jo.put("overloading",String.valueOf(0));
                //剩余库容
                jo.put("surplusCapacity",String.valueOf((storageCapacity-used)));
                jo.put("used",String.valueOf(used));
            }else{
                //超负荷
                jo.put("overloading",String.valueOf((used-storageCapacity)));

                //剩余库容
                jo.put("surplusCapacity",String.valueOf(0));
                jo.put("used",String.valueOf(used-(used-storageCapacity)));
            }
            jsonArray.add(jo);
        }
        return jsonArray;
    }


    @Override
    public JSONArray adpStockLargePartsAge(String modelPlats, String machineType, String factorytype,
                                           String factory, String largeparttype, String largepart) {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> sortMap = new HashMap<String,Object>();
        sortMap.put("modelPlats", modelPlats);
        sortMap.put("machineType", machineType);
        sortMap.put("factorytype", factorytype);
        sortMap.put("factory", factory);
        sortMap.put("largeparttype", largeparttype);
        sortMap.put("largepart", largepart);
        List<Map<String, Object>> assemblyPlantList = adpStockDelayMapper.assemblyPlant(sortMap);
        for (Map<String, Object> assemblyPlantMap : assemblyPlantList){
            JSONObject jo = new JSONObject();
            //总装厂名称
            jo.put("factoryName",(String)assemblyPlantMap.get("factory_name"));
            //总装厂经度
            jo.put("factoryLng",(String)assemblyPlantMap.get("factory_lng"));
            //总装厂纬度
            jo.put("factoryLat",(String)assemblyPlantMap.get("factory_lat"));
            String factoryCode = (String) assemblyPlantMap.get("factory_code");
            if(factoryCode!=null && !"".equals(factoryCode)){
                List<Map<String, Object>> largePartsTypeMaps = adpStockDelayMapper.largePartsTypeCount(factoryCode);
                String str = "";
                for(Map<String, Object> map : largePartsTypeMaps){
                    String largePartsType = (String) map.get("large_parts_type");
                    Long lptcount = (Long) map.get("lptcount");
                    str = str + ""+largePartsType+"  " +lptcount+" <br/>";
                }
                jo.put("largePart",str);
            }
            jsonArray.add(jo);
        }

        return jsonArray;
    }
}
