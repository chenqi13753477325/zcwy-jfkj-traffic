package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.ConstructionWarningDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预警告警-工程在建-接货预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningDeliveryController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:35
 * @Version: 1.0
 */
@RestController
@RequestMapping("delivery")
public class ConstructionWarningDeliveryController {

    private static final Logger logger = LoggerFactory.getLogger(ConstructionWarningDeliveryController.class);

    @Reference
    ConstructionWarningDeliveryService constructionWarningDeliveryService;

    /**
     * 接货-首页-预警数，接货量，接货周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 19:32
     */
    @RequestMapping(value = "warningDelivery",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray warningDelivery(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.warningDelivery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 接货-明细-基础具备吊装条件到首个大部件到货时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 13:18
     */
    @RequestMapping(value = "firstArrivalTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray firstArrivalTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.firstArrivalTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 接货-明细-到货未吊装时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:41
     */
    @RequestMapping(value = "arrivalNohoistingTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray arrivalNohoistingTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.arrivalNohoistingTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 接货-明细-主机到货未吊装数量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:41
     */
    @RequestMapping(value = "hostArrivalNohoistingTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray hostArrivalNohoistingTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.hostArrivalNohoistingTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 接货-明细-单台塔筒平均生产周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:41
     */
    @RequestMapping(value = "singleTowerCycel",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray singleTowerCycel(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.singleTowerCycel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 接货-明细-塔架到货未吊装时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/6 14:41
     */
    @RequestMapping(value = "towerArrivalNohoistingTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray towerArrivalNohoistingTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningDeliveryService.towerArrivalNohoistingTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }



}
