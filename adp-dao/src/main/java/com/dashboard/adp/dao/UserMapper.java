package com.dashboard.adp.dao;

import com.dashboard.adp.api.model.AdpBaseData;
import com.dashboard.adp.api.model.AdpBaseServiceDepartment;
import com.dashboard.adp.api.model.SysRoleUser;
import com.dashboard.adp.api.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * 2019/2/28
 * 对 User 表----查询所有的记录，插入，删除，修改，查询，
 *
 */
public interface UserMapper {


    Integer count(@Param("params") Map<String, Object> params);

    List<SysUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    SysUser usergetById(Long id);

    SysRoleUser userRolegetById(Long userId);

    int update(SysUser user);

    int deleteUserRole(@Param("userId") Long userId);

    int saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    int externalsynuser(Map<String,Object> userInfoMap);
    int externalSynUpdateUser(Map<String,Object> userInfoMap);

    List<Map<String,Object>> pernrcount(Map<String,Object> userInfoMap);

    List<AdpBaseServiceDepartment> listDepartmentAll();

    List<Map<String,Object>> listDepartmentMap();

    List<Map<String,Object>> listPjMap(@Param("idStr") String idStr,@Param("nameStr") String nameStr);

    List<Map<String,Object>> listPjMapByContractNoOrPjName(@Param("idStr") String idStr);

    List<Map<String,Object>> listPjMapByContractNoOrPjNameStr(@Param("idStr") String idStr);


    int deleteUserDepartment(@Param("userId") Long userId);

    int saveUserDepartment(@Param("userId") Long userId, @Param("pjPermissionId") String str,@Param("pjStyle") String pjStyle);

    List<AdpBaseServiceDepartment> getDepartmentByUserId(@Param("userId") Long userId);

    List<Map<String,Object>> listPjAll();

    List<AdpBaseData> getPjByUserId(@Param("userId") Long userId);

    List<AdpBaseData> getPjByUserIdPjStyle(@Param("userId") Long userId,@Param("pjStyle") String pjStyle);

    SysUser selectByPersonCode(@Param("personCode") String personCode);

    String getUserContractNo(@Param("contractNo") String contractNo);

    Integer updateSysUser(Map<String,Object> userInfoMap);

    List<Map<String,Object>>  listCustomerMap();

    List<Map<String,Object>> listPjMapByCustomer(@Param("idStr") String idStr,@Param("nameStr") String nameStr);

    String getUserCustomerNo(@Param("contractNo") String contractNo);
}
