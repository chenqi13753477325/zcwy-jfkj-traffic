package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.AdpStockDelayService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 各阶段情况-库存
 *
 * @Author: c7
 * @Filename: AdpStockDelayController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/11 19:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("adpStockDelay")
public class AdpStockDelayController {

    private static final Logger logger = LoggerFactory.getLogger(AdpStockDelayController.class);

    @Reference
    AdpStockDelayService adpStockDelayService;


    /**
     * 呆滞品超期集合
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/11 19:57
     */
    @RequestMapping(value = "/adpStockDelayList",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpStockDelayList(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpStockDelayService.adpStockDelayList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 库容负载状态
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/11 19:57
     */
    @RequestMapping(value = "/adpStorageLoad",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpStorageLoad(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpStockDelayService.adpStorageLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 库存资源分布图
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/12 17:40
     */
    @RequestMapping(value = "/adpStockLargePartsAge",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpStockLargePartsAge(@Param(value="modelPlats")String modelPlats, @Param(value="machineType")String machineType,
                                                         @Param(value="factorytype")String factorytype, @Param(value="factory")String factory,
                                                         @Param(value="largeparttype")String largeparttype, @Param(value="largepart")String largepart){
        JSONArray jsonArray = null;
        try {
            jsonArray = adpStockDelayService.adpStockLargePartsAge(modelPlats,machineType,factorytype,factory,largeparttype,largepart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }






}
