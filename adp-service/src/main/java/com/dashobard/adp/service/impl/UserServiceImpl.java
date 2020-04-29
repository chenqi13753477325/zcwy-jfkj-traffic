package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dashboard.adp.api.model.*;
import com.dashboard.adp.api.pageutil.Pagination;
import com.dashboard.adp.api.service.UserService;
import com.dashboard.adp.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * User Service
 *
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer count(Map<String, Object> params) {
        return userMapper.count(params);
    }

    @Override
    public List<SysUser> list(Map<String, Object> params, Integer offset, Integer limit) {
        return userMapper.list(params,offset,limit);
    }

    @Override
    public SysUser usergetById(Long id) {
        return userMapper.usergetById(id);
    }


    @Override
    public SysRoleUser userRolegetById(Long userId) {
        return userMapper.userRolegetById(userId);
    }


    @Override
    public SysUser updateUser(UserDto userDto) {
        //修改用户
        userMapper.update(userDto);
        //新增用户与角色关联关系
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        //用户id，关联事业部，关联项目
        saveUserDepartment(userDto.getId(),userDto.getDepartmentIds(),userDto.getPjIds(),userDto.getCustomerIds());
        return userDto;
    }

    @Override
    public int externalsynuser(Map<String, Object> userInfoMap) {
        int resultInt = 0;

        resultInt = userMapper.externalsynuser(userInfoMap);

        return resultInt;
    }

    @Override
    public SysUser selectByPersonCode(String personCode) {
        return userMapper.selectByPersonCode(personCode);
    }



    @Override
    public int externalSynUpdateUser(Map<String, Object> userInfoMap) {
        int resultInt = 0;
        resultInt = userMapper.externalSynUpdateUser(userInfoMap);
        return resultInt;
    }

    @Override
    public List<Map<String, Object>> pernrcount(Map<String, Object> userInfoMap) {
        List<Map<String, Object>> lstMap = null;
        lstMap = userMapper.pernrcount(userInfoMap);
        return lstMap;
    }

    /**
     * 保存用户与角色
     * @param userId
     * @param roleIds
     */
    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userMapper.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userMapper.saveUserRoles(userId, roleIds);
            }
        }
    }

    /**
     * 保存用户与事业部
     * @param userId
     * @param departmentIds
     */
    private void saveUserDepartment(Long userId, List<String> departmentIds,List<String> pjIds,List<String> customerIds) {
        userMapper.deleteUserDepartment(userId);
        if (departmentIds != null || pjIds != null) {
            if (departmentIds!=null &&  !CollectionUtils.isEmpty(departmentIds)) {
                for(String strs : departmentIds){
                    if(strs!=null && !"0".equals(strs)){
                        userMapper.saveUserDepartment(userId, strs," ");
                    }
                }
            }
            if (pjIds!=null && !CollectionUtils.isEmpty(pjIds)) {
               for(String str : pjIds){
                   if(str!=null && !"0".equals(str)){
                       userMapper.saveUserDepartment(userId, str,"1");
                   }
               }

            }
            if (customerIds!=null && !CollectionUtils.isEmpty(customerIds)) {
                for(String str : customerIds){
                    if(str!=null && !"0".equals(str)){
                        userMapper.saveUserDepartment(userId, str,"2");
                    }
                }

            }
        }
    }

    /**
     * 获取所有事业部
     * @return
     */
    @Override
    public List<AdpBaseServiceDepartment> listDepartmentAll() {
        return userMapper.listDepartmentAll();
    }

    /**
     * 通过用户id获取事业部编号
     * @param userId
     * @return
     */
    @Override
    public List<AdpBaseServiceDepartment> getDepartmentByUserId(Long userId) {
        return userMapper.getDepartmentByUserId(userId);
    }

    /**
     * 获取事业部与项目结合的list，
     * @return
     */
    @Override
    public List<Map<String, Object>> listPjMap(String txtObj) {
        List<Map<String, Object>> retMaps = new ArrayList();
        if(txtObj!=null && !"".equals(txtObj)){
            txtObj.lastIndexOf(",");
            List<Map<String, Object>> contractNoOrPjNameMap =null;
            if(txtObj.indexOf(",") >= 0){
                StringBuffer txtObjBufferTwo = new StringBuffer();
                String[] split = txtObj.split(",");
                for (int i = 0; i < split.length; i++) {
                    if (!"".equals(split[i]) && split[i] != null)
                        txtObjBufferTwo.append("'"+split[i]+"',");
                }
                String s = txtObjBufferTwo.deleteCharAt(txtObjBufferTwo.length() - 1).toString();
                String str2 = s.replaceAll(" ","");
                contractNoOrPjNameMap = userMapper.listPjMapByContractNoOrPjName(str2);
            }else{
                contractNoOrPjNameMap = userMapper.listPjMapByContractNoOrPjNameStr(txtObj);
            }
            if(contractNoOrPjNameMap!=null && contractNoOrPjNameMap.size()>0){
                for(Map map : contractNoOrPjNameMap){
                    Map<String, Object> retMap = new HashMap<String, Object>();
                    retMap.put("id", (String) map.get("id"));
                    retMap.put("name", (String) map.get("name"));
                    retMap.put("pid", (String) map.get("pid"));
                    retMaps.add(retMap);
                }
            }
        }else {
            List<Map<String, Object>> mapsList = userMapper.listDepartmentMap();
            Map<String, Object> mapOther = new HashMap();
            mapOther.put("id", "10");
            mapOther.put("name", "");
            mapOther.put("pid", "0");
            mapsList.add(mapOther);
            for (Map<String, Object> map : mapsList) {
                Map<String, Object> retMap = new HashMap<String, Object>();
                retMap.put("id", (String) map.get("id"));
                retMap.put("pid", (String) map.get("pid"));
                String nameStr = (String) map.get("name");
                if (nameStr != null && !"".equals(nameStr)) {
                    retMap.put("name", (String) map.get("name"));
                } else {
                    retMap.put("name", "其他");
                }
                retMaps.add(retMap);
                List<Map<String, Object>> maps = userMapper.listPjMap((String) map.get("id"), (String) map.get("name"));
                for (Map<String, Object> asdMap : maps) {
                    Map<String, Object> asddddd = new HashMap<String, Object>();
                    asddddd.put("id", (String) asdMap.get("id"));
                    asddddd.put("name", (String) asdMap.get("name"));
                    asddddd.put("pid", (String) asdMap.get("pid"));
                    retMaps.add(asddddd);
                }
            }
        }
        return retMaps;
    }

    /**
     * 通过用户id获取项目订单编号
     * @param userId
     * @return
     */
    @Override
    public List<AdpBaseData> getPjByUserId(Long userId) {
        return userMapper.getPjByUserId(userId);
    }

    @Override
    public List<AdpBaseData> getPjByUserIdPjStyle(Long userId, String pjStyle) {
        return userMapper.getPjByUserIdPjStyle(userId,pjStyle);
    }



    /**
     * 通过项目的订单编号获取事业部编号
     * @param contractNo
     * @return
     */
    @Override
    public String getUserContractNo(String contractNo) {
        return userMapper.getUserContractNo(contractNo);
    }



    /**
     * 获取所有项目列表
     * @return
     */
    @Override
    public List<Map<String,Object>> listPjAll() {
        return userMapper.listPjAll();
    }

    /**
     * 修改用户登录ip,登录时间
     * @param userName
     * @param remoteAddr
     * @param userLoginTime
     * @return
     */
    @Override
    public Integer updateSysUser(String userName,String remoteAddr, Instant userLoginTime) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName",userName);
        userMap.put("loginIp",remoteAddr);
        userMap.put("userLoginTime",userLoginTime);
        return userMapper.updateSysUser(userMap);
    }


    /**
     * 获取客户与项目结合的list，
     * @return
     */
    @Override
    public List<Map<String, Object>> listCustomerMap() {
        List<Map<String, Object>> retMaps = new ArrayList();
        List<Map<String, Object>> mapsList = userMapper.listCustomerMap();
        for(Map<String, Object> map : mapsList){
            Map<String, Object> retMap = new HashMap<String, Object>();
            retMap.put("id",(String)map.get("id"));
            retMap.put("name",(String)map.get("name"));
            retMap.put("pid",(String)map.get("pid"));
            retMaps.add(retMap);
            List<Map<String, Object>> maps = userMapper.listPjMapByCustomer((String) map.get("id"), (String) map.get("name"));
            for(Map<String, Object> asdMap : maps){
                Map<String, Object> asddddd = new HashMap<String, Object>();
                asddddd.put("id",(String) asdMap.get("id"));
                asddddd.put("name",(String) asdMap.get("name"));
                asddddd.put("pid",(String) asdMap.get("pid"));
                retMaps.add(asddddd);
            }
        }
        return retMaps;
    }

    @Override
    public String getUserCustomerNo(String contractNo) {
        return userMapper.getUserCustomerNo(contractNo);
    }
}
