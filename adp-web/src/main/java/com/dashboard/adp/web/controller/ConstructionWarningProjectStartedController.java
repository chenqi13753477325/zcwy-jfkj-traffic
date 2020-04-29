package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.ConstructionWarningProjectStartedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 预警告警-工程建设-项目开工预警
 *
 * @Author c7
 * @Filename: ConstructionWarningProjectStartedController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/11/26 15:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("projectStarted")
public class ConstructionWarningProjectStartedController {

    private static final Logger logger = LoggerFactory.getLogger(ConstructionWarningProjectStartedController.class);

    @Reference
    ConstructionWarningProjectStartedService  constructionWarningProjectStartedService;

    /**
     * 开工-首页-预警数，项目计划开工率
     * @param
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 16:05
     */
    @RequestMapping(value = "/customer",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray customer(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.cwpsCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 预警告警-工程建设-微观指标对应条数，比率
     * @param
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 17:43
     */
    @RequestMapping(value = "/countAndRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray countAndRatio(HttpServletRequest request,String startWorkType){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.countAndRatio(startWorkType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 开工-明细-三证完成率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 13:41
     */
    @RequestMapping(value = "/threeEvidenceRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray threeEvidenceRatio(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.threeEvidenceRatio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 开工-明细-四标定标率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 13:41
     */
    @RequestMapping(value = "/calibrationRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray calibrationRatio(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.calibrationRatio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
    
    /**
     * 开工-明细-四标进场率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:23
     */
    @RequestMapping(value = "/fourStandardRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray fourStandardRatio(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.fourStandardRatio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 开工-明细-基础施工开工率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:23
     */
    @RequestMapping(value = "/baseCommRateRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray baseCommRateRatio(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.baseCommRateRatio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 开工-明细-机位齐套率
     * @param  [request]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/5 14:23
     */
    @RequestMapping(value = "/completeRateRatio",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray completeRateRatio(HttpServletRequest request){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.completeRateRatio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 三证完成率相关的项目
     * @param  [request, threeEvidence]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/11/29 17:44
     */
    @RequestMapping(value = "/threeEvidenceProject",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray threeEvidenceProject(HttpServletRequest request,
                                                        @RequestParam(name="threeEvidence",required = true) Integer threeEvidence){
        JSONArray jsonArray = null;
        try {
            jsonArray = constructionWarningProjectStartedService.threeEvidenceProject(threeEvidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }







}
