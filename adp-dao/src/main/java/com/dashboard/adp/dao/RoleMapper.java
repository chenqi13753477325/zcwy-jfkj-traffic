package com.dashboard.adp.dao;

import com.dashboard.adp.api.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 *
 * 2019/2/28
 *
 */
public interface RoleMapper {

    String getRoleByUI(String uid);

    List<SysRole> getRoles(String uid);

    int count(@Param("params") Map<String, Object> params);

    List<SysRole> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    @Select("select * from sys_role t where t.\"name\" = #{name}")
    SysRole getRole(String name);



    int save(SysRole role);

    int saveRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    @Select("select * from sys_role t where t.id = #{id}")
    SysRole getSysRoleById(Long id);

    @Update("update sys_role set \"name\" = #{name}, \"description\" = #{description}, \"updateTime\" = now(),\"level\" = #{level} where id = #{id}")
    int update(SysRole role);

    @Delete("delete from sys_role_permission where \"roleId\" = #{roleId}")
    int deleteRolePermission(Long roleId);


    List<SysRole> roleAll();

    @Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.\"roleId\"  where ru.\"userId\"  = #{userId}")
    List<SysRole> listByUserId(Long userId);


    Integer getRoleIdByUserIds(@Param("userName") String userName);

}
