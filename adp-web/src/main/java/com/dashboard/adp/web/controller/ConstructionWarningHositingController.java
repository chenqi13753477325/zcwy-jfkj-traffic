package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.ConstructionWarningHositingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预警告警-工程在建-吊装预警
 *
 * @Author: c7
 * @Filename: ConstructionWarningHositingController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/5 14:35
 * @Version: 1.0
 */
@RestController
@RequestMapping("hositing")
public class ConstructionWarningHositingController {

    private static final Logger logger = LoggerFactory.getLogger(ConstructionWarningHositingController.class);

    @Reference
    ConstructionWarningHositingService constructionWarningHositingService;

    /**
     * 吊装-首页-预警数，新增装机量，吊装周期
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 15:02
     */
    @RequestMapping(value = "warningDelivery",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray warningHositing(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.warningHositing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-项目计划开吊率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 15:02
     */
    @RequestMapping(value = "pjPlanHositingRate",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray pjPlanHositingRate(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.pjPlanHositingRate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-主吊数量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 16:40
     */
    @RequestMapping(value = "mainCraneNumber",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray mainCraneNumber(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.mainCraneNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-首台齐套到货到首吊时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 16:53
     */
    @RequestMapping(value = "firstArrivedCompleteTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray firstArrivedCompleteTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.firstArrivedCompleteTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-首台吊装预备齐套数
     * @param
     * @return
     * @date   2018/12/7 17:11
     */
    @RequestMapping(value = "firstHositingPrepareNumber",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray firstHositingPrepareNumber(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.firstHositingPrepareNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-主吊进场后准备时长
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 17:42
     */
    @RequestMapping(value = "mainCranePrepareTime",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray mainCranePrepareTime(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.mainCranePrepareTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 吊装-明细-单台主吊作业效率
     * @param
     * @return
     * @date   2018/12/7 17:52
     */
    @RequestMapping(value = "singleCraneRate",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray singleCraneRate(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.singleCraneRate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 吊装-明细-吊车转场效率
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 17:59
     */
    @RequestMapping(value = "craneTransfer",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray craneTransfer(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.craneTransfer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 吊装-明细-吊装未调试量
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/7 18:10
     */
    @RequestMapping(value = "hositingNodebugNumber",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JSONArray hositingNodebugNumber(){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningHositingService.hositingNodebugNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }





}
