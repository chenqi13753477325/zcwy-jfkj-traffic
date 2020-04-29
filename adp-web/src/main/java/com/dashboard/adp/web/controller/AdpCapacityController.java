package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.AdpCapacityService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 各阶段-生产
 *
 * @Author: c7
 * @Filename: AdpCapacityController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/25 11:42
 * @Version: 1.0
 */
@RestController
@RequestMapping("adpCapacity")
public class AdpCapacityController {

    private static final Logger logger = LoggerFactory.getLogger(AdpCapacityController.class);

    @Reference
    AdpCapacityService adpCapacityService;

    /**
     * 页片总产能，机舱总产能，叶轮总产能，电控总产能
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 11:54
     */
    @RequestMapping(value = "totalCapacity",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpCapacityList(@Param(value="dateStart")String dateStart, @Param(value="dateEnd")String dateEnd,
                                                   @Param(value="modelPlats")String modelPlats, @Param(value="machineType")String machineType,
                                                   @Param(value="factorytype")String factorytype, @Param(value="factory")String factory,
                                                   @Param(value="largeparttype")String largeparttype, @Param(value="largepart")String largepart){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpCapacityService.totalCapacity(dateStart,dateEnd,modelPlats,machineType,factorytype,factory,largeparttype,largepart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 地图产能分布
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 16:02
     */
    @RequestMapping(value = "mapCapacity",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray mapCapacity(@Param(value="dateStart")String dateStart, @Param(value="dateEnd")String dateEnd,
                                                @Param(value="modelPlats")String modelPlats, @Param(value="machineType")String machineType,
                                                @Param(value="factorytype")String factorytype, @Param(value="factory")String factory,
                                                @Param(value="largeparttype")String largeparttype, @Param(value="largepart")String largepart){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpCapacityService.mapCapacity(dateStart,dateEnd,modelPlats,machineType,factorytype,factory,largeparttype,largepart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 产能列表
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/25 17:52
     */
    @RequestMapping(value = "listCapacity",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray listCapacity(@Param(value="dateStart")String dateStart, @Param(value="dateEnd")String dateEnd,
                                                @Param(value="modelPlats")String modelPlats, @Param(value="machineType")String machineType,
                                                @Param(value="factorytype")String factorytype, @Param(value="factory")String factory,
                                                @Param(value="largeparttype")String largeparttype, @Param(value="largepart")String largepart){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpCapacityService.listCapacity(dateStart,dateEnd,modelPlats,machineType,factorytype,factory,largeparttype,largepart    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }






}
