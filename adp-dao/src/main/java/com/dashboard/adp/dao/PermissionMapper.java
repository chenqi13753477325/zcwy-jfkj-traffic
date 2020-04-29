package com.dashboard.adp.dao;

import com.dashboard.adp.api.model.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *
 *  2019/2/28
 */
public interface PermissionMapper {

    List<SysPermission> getPerListByUid(@Param("userId") Long userId);


    Integer count(@Param("params") Map<String, Object> params);

    List<SysPermission> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);


    List<Map<String, Object>> listAll();

    List<Map<String, Object>> listAlls();

    /*@Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.\"permissionId\" where rp.\"roleId\" = #{roleId} order by p.sort")*/
    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.\"permissionId\" where rp.\"roleId\" = #{roleId}")
    List<SysPermission> listPerByRoleId(Long roleId);



}
