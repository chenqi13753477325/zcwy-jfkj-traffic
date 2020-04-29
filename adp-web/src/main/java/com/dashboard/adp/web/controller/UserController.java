package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.model.*;
import com.dashboard.adp.api.service.PermissionService;
import com.dashboard.adp.api.service.RoleService;
import com.dashboard.adp.api.service.UserService;
import com.dashboard.adp.api.utils.IDUtils;
import com.dashboard.adp.web.page.table.PageTableHandler;
import com.dashboard.adp.web.page.table.PageTableRequest;
import com.dashboard.adp.web.page.table.PageTableResponse;
import net.sf.json.JSONArray;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.dashboard.adp.api.utils.JsonUtils;
import com.dashboard.adp.api.utils.ResultUtils;
import com.dashboard.adp.api.vo.Result;

import java.io.File;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author admin
 * User controller
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String PERNRCODE = "pernrcode";
    private static final String XFORWARDEDFOR = "x-forwarded-for";
    private static final String PROXYCLIENTREMOTEADDR = "Proxy-Client-remoteAddr";
    private static final String WLPROXYCLIENTREMOTEADDR = "WL-Proxy-Client-remoteAddr";
    private static final String UNKNOWN = "unknown";

    @Reference
    UserService userService;

    @Reference
    PermissionService permissionService;

    @Reference
    RoleService roleService;




    @RequestMapping("/listUsers")
    public PageTableResponse listUsers(HttpServletRequest requests) {
        PageTableRequest request = new PageTableRequest();
        Map<String,Object> map = new HashMap<String,Object>(10);
        map.put("username",requests.getParameter("username"));
        map.put("nickname",requests.getParameter("nickname"));
        request.setParams(map);
        request.setLimit(Integer.valueOf(requests.getParameter("length")));
        request.setOffset(Integer.valueOf(requests.getParameter("start")));

        PageTableResponse handle = new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return userService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<SysUser> list(PageTableRequest request) {
                List<SysUser> list = userService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);

        return handle;
    }


    /**
     * 跳转到用户管理
     * @return
     */
    @RequestMapping("/user_list.html")
    public ModelAndView adminList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user/user_list");
        return mv;
    }


    /**
     * 跳转到编辑用户页面
     * @return
     */
    @GetMapping("/user_update.html")
    public ModelAndView userUpdate(@RequestParam(value="id",required=false) String id){
        SysUser sysUser = userService.usergetById(Long.valueOf(id));
        SysRoleUser sysRoleUser = userService.userRolegetById(Long.valueOf(id));

        List<SysRole> sysRoles = roleService.roleAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("id",id);
        mv.addObject("username",sysUser.getUsername());
        mv.addObject("phone",sysUser.getPhone());
        mv.addObject("nickname",sysUser.getNickname());
        mv.addObject("status",sysUser.getStatus());
        if(sysRoleUser!=null){
            mv.addObject("roleUserId",sysRoleUser.getRoleId());
        }
        mv.addObject("sysRoles",sysRoles);
        mv.setViewName("user/user_update");
        return mv;
    }

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    @PostMapping("/updateUsers")
    public SysUser updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }



    /**
     * 同步用户数据
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value="/synaccount",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody
    Result synaccount(@RequestBody String jsonStr,HttpServletRequest request){
        Result result = ResultUtils.success();
        try{
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            int resultInt = 0;
            Map<String,Object> param = new HashMap<String,Object>(10);
            param.put("pernrname",map.get("pernrname").toString());
            param.put("pernrcode",map.get("pernrcode").toString());

            List<Map<String,Object>> lstMap = userService.pernrcount(param);
            if(lstMap!=null && lstMap.size()==1
                    && Integer.valueOf(lstMap.get(0).get("count").toString()).intValue()==0)
            {
                /*Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);*/

                Instant timestamp = Instant.now();
                map.put("createtime",timestamp);
                map.put("id",IDUtils.getId());
                resultInt = userService.externalsynuser(map);
                if(resultInt>0)
                {
                    //创建账号成功
                    result.setResultCode("000");
                    result.setResultInfo("创建账号成功");
                }
                else
                {
                    result = ResultUtils.failure("账号创建失败");
                    //账号创建失败
                    result.setResultCode("002");
                    result.setResultInfo("账号创建失败");
                }
            }else{
                //账号已存在
                result.setResultCode("001");
                result.setResultInfo("账号已存在");
            }

        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
            //账号创建失败
            result.setResultCode("002");
            //账号创建失败
            result.setResultInfo("账号创建失败");
        }
        return  result;
    }

    /**
     * 修改用户数据
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value="/updateaccount",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody
    Result updateaccount(@RequestBody String jsonStr,HttpServletRequest request){
        Result result = ResultUtils.success();
        try{
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            if(map.containsKey(PERNRCODE)) {
                int resultInt = 0;
                Map<String, Object> param = new HashMap<String, Object>(10);
                param.put("pernrcode", map.get("pernrcode").toString());

                List<Map<String, Object>> lstMap = userService.pernrcount(param);
                if (lstMap != null && lstMap.size() == 1
                        && Integer.valueOf(lstMap.get(0).get("count").toString()).intValue() == 0) {
                    //账号不存在
                    result.setResultCode("101");
                    result.setResultInfo("账号不存在");

                } else {
                    Instant timestamp = Instant.now();
                    map.put("updatetime", timestamp);
                    resultInt = userService.externalSynUpdateUser(map);
                    if (resultInt > 0) {
                        //修改账号成功
                        result.setResultCode("100");
                        result.setResultInfo("修改账号成功");
                    } else {
                        result = ResultUtils.failure("修改账号失败");
                        //修改账号失败
                        result.setResultCode("102");
                        result.setResultInfo("修改账号失败");
                    }
                }
            }
            else{
                //账号不存在
                result.setResultCode("101");
                result.setResultInfo("账号不存在");
            }
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
            //修改账号失败
            result.setResultCode("102");
            //修改账号失败
            result.setResultInfo("修改账号失败");
        }
        return  result;
    }


    /**
     * 修改用户数据
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value="/disableaccount",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody
    Result disableaccount(@RequestBody String jsonStr,HttpServletRequest request){
        Result result = ResultUtils.success();
        try{
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            if(map.containsKey(PERNRCODE) && map.size()==1) {
                int resultInt = 0;
                //获取账号数
                List<Map<String, Object>> lstMap = userService.pernrcount(map);

                if (lstMap != null && lstMap.size() == 1
                        && Integer.valueOf(lstMap.get(0).get("count").toString()).intValue() == 0) {
                    //账号不存在
                    result.setResultCode("201");
                    result.setResultInfo("账号不存在");
                } else {
                    Instant timestamp = Instant.now();
                    map.put("updatetime", timestamp);
                    map.put("userstatus", 0);
                    resultInt = userService.externalSynUpdateUser(map);
                    if (resultInt > 0) {
                        //账号禁用成功
                        result.setResultCode("200");
                        result.setResultInfo("账号禁用成功");
                    } else {
                        result = ResultUtils.failure("账号禁用失败");
                        //账号禁用失败
                        result.setResultCode("202");
                        result.setResultInfo("账号禁用失败");
                    }
                }
            }
            else{
                //账号不存在
                result.setResultCode("201");
                result.setResultInfo("账号不存在");
            }
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
            //账号禁用失败
            result.setResultCode("202");
            //账号禁用失败
            result.setResultInfo("账号禁用失败");
        }
        return  result;
    }


    /**
     * 获取所有事业部
     * @return
     */
    @RequestMapping("/listDepartmentAll")
    public Object listDepartmentAll() {
        List<AdpBaseServiceDepartment> list = userService.listDepartmentAll();
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }

    /**
     * 获取用户关联的事业部
     * @param userId
     * @return
     */
    @RequestMapping("/getDepartmentByUserId")
    public Object getDepartmentByUserId(@RequestParam(value="userId",required=false) String userId) {
        List<AdpBaseServiceDepartment> adpBaseServiceDepartment = userService.getDepartmentByUserId(Long.valueOf(userId));
        Long[] age = new Long[adpBaseServiceDepartment.size()];
        for(int i=0;i<adpBaseServiceDepartment.size();i++){
            age[i] = Long.valueOf(adpBaseServiceDepartment.get(i).getServiceDepartmentCode());
        }
        return age;
    }



    /**
     * 获取所有项目列表
     * @return
     */
    @RequestMapping("/listPjAll")
    public Object listPjAll() {
        List<Map<String,Object>> list = userService.listPjAll();
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }


    /**
     * 通过用户id获取项目订单编号-事业部tree使用
     * @param userId
     * @return
     */
    @RequestMapping("/getPjByUserId")
    public Object getPjByUserId(@RequestParam(value="userId",required=false) String userId) {
        List<AdpBaseData> adpBaseServiceDepartment = userService.getPjByUserIdPjStyle(Long.valueOf(userId),"1");
        Object[] age = new Object[adpBaseServiceDepartment.size()];
        Object[] aget = new Object[adpBaseServiceDepartment.size()];
        if(adpBaseServiceDepartment!=null && adpBaseServiceDepartment.size()>0){
            for(int i=0;i<adpBaseServiceDepartment.size();i++){
                age[i] = adpBaseServiceDepartment.get(i).getContractNo();
                String userContractNo = userService.getUserContractNo(adpBaseServiceDepartment.get(i).getContractNo());
                if(userContractNo!=null && !"".equals(userContractNo)){
                    aget[i] = userContractNo;
                }else{
                    aget[i] = "10";
                }

            }
        }
        return  ArrayUtils.addAll(age, aget);
    }

    /**
     * 获取所有项目与事业部结合list
     * @return
     */
    @RequestMapping("/listPjMap")
    public com.alibaba.fastjson.JSONArray listPjMap(@RequestParam(value="txtObj",required=false) String txtObj ) {
        List<Map<String, Object>> maps = userService.listPjMap(txtObj);
        com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
        setPermissionsTree("0", maps, array);
        return array;
    }

    /**
     * 项目与事业部结合list的Tree处理
     * @param pId
     * @param permissionsAll
     * @param array
     */
    private void setPermissionsTree(String pId, List<Map<String, Object>> permissionsAll, com.alibaba.fastjson.JSONArray array) {
        for (Map<String, Object> per : permissionsAll) {
            String pid = (String) per.get("pid");
            if (pid.equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);

                if (permissionsAll.stream().filter(p -> p.get("pid").equals(per.get("id"))).findAny() != null) {
                    com.alibaba.fastjson.JSONArray child = new com.alibaba.fastjson.JSONArray();
                    parent.put("child", child);
                    setPermissionsTree((String) per.get("id"), permissionsAll, child);
                }
            }
        }
    }


    /**
     * 修改用户登录ip,登录时间
     * @param request
     * @param userName
     */
    @RequestMapping("/updateSysUser")
    private void updateSysUser(HttpServletRequest request,
                               @RequestParam(value="userName",required=false) String userName){

        String remoteAddr = request.getHeader(XFORWARDEDFOR);
        if(remoteAddr == null || remoteAddr.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader(PROXYCLIENTREMOTEADDR);
        }
        if(remoteAddr == null || remoteAddr.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader(WLPROXYCLIENTREMOTEADDR);
        }
        if(remoteAddr == null || remoteAddr.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }
        Instant userLoginTime = Instant.now();
        Integer retInt = userService.updateSysUser(userName, remoteAddr, userLoginTime);
        if(retInt!=null && retInt>0){
            System.out.println("保存成功");
        }else{
            System.out.println("保存失败");
        }
    }


    /**
     * 获取所有项目与客户结合list
     * @return
     */
    @RequestMapping("/listCustomerMap")
    public com.alibaba.fastjson.JSONArray listCustomerMap() {
        List<Map<String, Object>> maps = userService.listCustomerMap();
        com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
        setPermissionsTree("0", maps, array);
        return array;
    }


    /**
     * 通过用户id获取项目订单编号-客户tree使用
     * @param userId
     * @return
     */
    @RequestMapping("/getPjByUserIdCustomer")
    public Object getPjByUserIdCustomer(@RequestParam(value="userId",required=false) String userId) {
        List<AdpBaseData> adpBaseServiceDepartment = userService.getPjByUserIdPjStyle(Long.valueOf(userId),"2");
        Object[] age = new Object[adpBaseServiceDepartment.size()];
        Object[] aget = new Object[adpBaseServiceDepartment.size()];
        for(int i=0;i<adpBaseServiceDepartment.size();i++){
            age[i] = adpBaseServiceDepartment.get(i).getContractNo();
            aget[i] = userService.getUserCustomerNo(adpBaseServiceDepartment.get(i).getContractNo());
        }
        return  ArrayUtils.addAll(age, aget);
    }


}
