package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.IpasProjMthPlanService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各阶段情况-供应资源
 *
 * @Author: c7
 * @Filename: IpasProjMthPlanController
 * @Email: chenq@icst.com.cn
 * @Date: 2018/12/26 15:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("ipasProjMthPlan")
public class IpasProjMthPlanController {
    private static final Logger logger = LoggerFactory.getLogger(IpasProjMthPlanController.class);

    @Reference
    IpasProjMthPlanService ipasProjMthPlanService;


    /**
     * 主需求计划版本变更
     * @param  [month]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 15:47
     */
    @RequestMapping(value = "list",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray list(@Param(value="contractNo") String contractNo){
        JSONArray jsonArray = null;
        try {
            jsonArray = ipasProjMthPlanService.list(contractNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 承运商资源负荷
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 17:37
     */
    @RequestMapping(value = "adpPlanCarrieResourceLoad",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpPlanCarrieResourceLoad(){
        JSONArray jsonArray = null;
        try {
            jsonArray = ipasProjMthPlanService.adpPlanCarrieResourceLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 产能资源负荷
     * @param  []
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/26 17:54
     */
    @RequestMapping(value = "adpCapacityTb",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpStorageLoad(String month){
        JSONArray jsonArray = null;
        try {
            jsonArray = ipasProjMthPlanService.adpCapacityTb(month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 项目分布
     * @param  [projectId]
     * @return com.alibaba.fastjson.JSONArray
     * @date   2018/12/27 13:54
     */
    @RequestMapping(value = "adpPlanProjectMap",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody JSONArray adpPlanProjectMap(String projectId){
        JSONArray jsonArray = null;
        try {
            jsonArray = ipasProjMthPlanService.adpPlanProjectMap(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }




}
