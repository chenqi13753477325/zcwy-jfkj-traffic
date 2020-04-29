package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.pageutil.Pagination;
import com.dashboard.adp.api.service.UserService;
import com.dashboard.adp.web.bean.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;

@Controller
public class TestController extends BaseController{



    @Reference
    UserService userService;

    @RequestMapping("test")
    public JsonResponse test() {
        return JsonResponse.success();
    }

    /*@RequestMapping("login.html")
    public String login() {
        return "test";
    }*/

    @RequestMapping("testbaife")
    public Object testbaifei(Integer count,Integer cwpsPlanStartCount) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        Double d = 0.0;
        if(count == 0){
            d = 0.0;
            nf.format(d);
        }else{
            Integer cc = count - cwpsPlanStartCount;
            if(cc%count == 0){
                int i = cc / count;
                nf.format(i);
            }else{
                d = (double) cc /count;
                nf.format(d);
            }
        }
        return nf.toString();
    }


}
