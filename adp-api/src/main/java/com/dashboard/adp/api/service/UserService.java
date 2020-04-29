package com.dashboard.adp.api.service;

import com.dashboard.adp.api.model.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenqi
 * User Service
 *
 */
public interface UserService {

    /**
     * 用户总条数
     * @param params
     * @return
     */
    Integer count(Map<String, Object> params);

    /**
     * 用户列表
     * @param params
     * @param offset
     * @param limit
     * @return
     */
    List<SysUser> list(Map<String, Object> params,Integer offset,Integer limit);

    SysUser selectByPersonCode(String personCode);

    /**
     * 通过用户id获取用户信息
     * @param id
     * @return
     */
    SysUser usergetById(Long id);

    SysRoleUser userRolegetById(Long userId);

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    SysUser updateUser(UserDto userDto);

    int externalsynuser(Map<String,Object> userInfoMap);
    int externalSynUpdateUser(Map<String,Object> userInfoMap);

    List<Map<String,Object>> pernrcount(Map<String,Object> userInfoMap);
    /**
     * 获取所有事业部
     * @return
     */
    List<AdpBaseServiceDepartment> listDepartmentAll();

    /**
     * 获取用户关联的事业部
     * @param userId
     * @return
     */
    List<AdpBaseServiceDepartment> getDepartmentByUserId(Long userId);

    /**
     * 获取所有事业部与项目结合list
     * @return
     */
    List<Map<String,Object>> listPjMap(String txtObj);

    /**
     * 获取所有项目列表
     * @return
     */
    List<Map<String,Object>> listPjAll();

    List<AdpBaseData> getPjByUserId(Long userId);

    List<AdpBaseData> getPjByUserIdPjStyle(Long userId,String pjStyle);

    /**
     * 通过订单号获取事业部编号
     * @param contractNo
     * @return
     */
    String getUserContractNo(String contractNo);

    /**
     * 用户登录时间，ip
     * @param remoteAddr
     * @param userLoginTime
     */
    Integer updateSysUser(String userName,String remoteAddr, Instant userLoginTime);

    /**
     * 获取客户与项目结合的list
     * @return
     */
    List<Map<String,Object>> listCustomerMap();

    /**
     * 通过订单编号获取客户名称
     * @param contractNo
     * @return
     */
    String getUserCustomerNo(String contractNo);
}
