package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dashboard.adp.api.service.BaseDataService;
import com.dashboard.adp.api.utils.FileUploadUtil;
import com.dashboard.adp.api.utils.JsonUtils;
import com.dashboard.adp.api.vo.Result;
import com.dashboard.adp.api.utils.ResultUtils;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright © 2018. All rights reserved.
 *
 * @Author: FreezeSoul
 * @CreateDate: 2018/8/6 17:16
 * @Description: BaseController
 * @Version: V1.0
 */
@CrossOrigin
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Reference
    BaseDataService baseService;

    @Value("${vedioPath}")
    private String  vedioPath;

    /*
     * 功能描述: 上传视频
     * @Param: [file]
     * @Return: com.dashboard.adp.api.vo.Result
     * @Author: lihx
     * @Date: 2019/12/5 10:50
     */
    @RequestMapping(value = "/uploadVideo" ,method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody
    Result uploadAttach(@RequestParam("file") MultipartFile file,
                        @RequestParam("contractNo") String contractNo,
                        @RequestParam("month") String month,
                        HttpServletRequest request, HttpServletResponse response)throws IllegalArgumentException, IOException {

        Result result = null;
        if (!file.isEmpty()) {
            try {
                //Map<String, Object> params = JsonUtils.toObject(jsonStr, Map.class);
                //订单编号
                if (contractNo == null || "".equals(contractNo)) {
                    return ResultUtils.failure("订单编号不能为空，请重试！");
                }
                //月份
                if (month == null || "".equals(month)) {
                    return ResultUtils.failure("月份不能为空，请重试！");
                }
                String contractNot = contractNo;
                Map paramMap = new HashMap();
                //视频时长
                String fileTime = ReadVideoTimeMs(file);
                String[] split = fileTime.split(":");
                long nm = 1000 * 60;// 一分钟的毫秒数
                long ns = 1000;// 一秒钟的毫秒数
                String s = split[0];
                String ss = split[1];
                Long aLong = Long.valueOf(s);
                Long bLong = Long.valueOf(ss);
                long aa = aLong * nm;
                long bb = bLong * ns;



                //文件名称
                String originalFilename = file.getOriginalFilename();

                //拼接后的文件名
                String fileName = contractNo+"_"+month+"_"+originalFilename;
                //创建文件路径
                Map<String, String> map = FileUploadUtil.uploadAttachAndReturnUrl(file);
                String url = map.get("url");

                paramMap.put("contractNo",contractNot);
                paramMap.put("videoName",fileName);
                paramMap.put("month",month);
                paramMap.put("videoPath",url);
                paramMap.put("fileTime",String.valueOf(aa+bb));
                //文件存放的绝对位置
                paramMap.put("vedioPath",vedioPath);
                baseService.saveVideoInfo(paramMap);
                //basePath拼接完成后，形如：http://192.168.1.20:8080
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                paramMap.put("basePath",basePath+url);
                result = ResultUtils.success(paramMap);
            } catch (Exception e) {
                logger.error("捕获未知异常信息" + e);
                result = ResultUtils.failure(e.getMessage());
            }
            return result;
        } else {
            result = ResultUtils.failure("上传的文件不存在");
            return result;
        }
    }

    /*
     * 功能描述: 视频列表
     * @Param: [file]
     * @Return: com.dashboard.adp.api.vo.Result
     * @Author: lihx
     * @Date: 2019/12/17 13:40
     */
//    @RequestMapping(value = "/videoList" ,method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public  @ResponseBody
//    Result videoList(@RequestBody(required = false) String jsonStr,HttpServletRequest request, HttpServletResponse response) {
//
//        Result result = null;
//            try {
//                Map<String, String> map = JsonUtils.toObject(jsonStr, Map.class);
//                List<Map<String,Object>> videoList = baseService.videoList(map);
//                result = ResultUtils.success(videoList);
//            } catch (Exception e) {
//                logger.error("捕获未知异常信息" + e);
//                result = ResultUtils.failure(e.getMessage());
//            }
//            return result;
//    }


    public String ReadVideoTimeMs(MultipartFile file) {
        Encoder encoder = new Encoder();
        long ms =
                0;
        try {
            // 获取文件类型
            String fileName = file.getContentType();
            // 获取文件后缀
            String pref = fileName.indexOf("/") != -1 ? fileName.substring(fileName.lastIndexOf("/") +
                    1, fileName.length()) :
                    null;
            String prefix =
                    "." + pref;
            // 用uuid作为文件名，防止生成的临时文件重复
            final File excelFile = File.createTempFile(UUID.randomUUID().toString().replace("-",
                    ""), prefix);
            // MultipartFile to File
            file.transferTo(excelFile);
            MultimediaInfo m = encoder.getInfo(excelFile);
            ms = m.getDuration();
            //程序结束时，删除临时文件
            excelFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int ss =
                1000;
        int mi = ss *
                60;
        int hh = mi *
                60;
        int dd = hh *
                24;
        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        String strHour = hour <
                10 ?
                "0" + hour :
                "" + hour;        //小时

        String strMinute = minute <
                10 ?
                "0" + minute :
                "" + minute;        //分钟

        String strSecond = second <
                10 ?
                "0" + second :
                "" + second;        //秒

        if (strHour.equals("00")) {
            return strMinute +
                    ":" + strSecond;
        } else {
            return strHour +
                    ":" + strMinute +
                    ":" + strSecond;
        }
    }


    /**
     * @Description: 各阶段情况-计划页面，模板生产
     * @Param: [jsonStr, request, response]
     * @Return: com.dashboard.adp.api.vo.Result
     * @Date: 2020/4/21 14:02
     */
    @RequestMapping(value = "/templetList" ,method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody
    Result templetList(@RequestBody(required = false) String jsonStr,
                     HttpServletRequest request, HttpServletResponse response) {

        Result result = null;
            try {
                Map<String, Object> map = JsonUtils.toObject(jsonStr, Map.class);
                List<Map<String,Object>> videoList = baseService.templetList(map);
                result = ResultUtils.success(videoList);
            } catch (Exception e) {
                logger.error("捕获未知异常信息" + e);
                result = ResultUtils.failure(e.getMessage());
            }
            return result;
    }
}
