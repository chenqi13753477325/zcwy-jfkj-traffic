package com.dashboard.adp.api.service;

import com.dashboard.adp.api.model.RoleDto;
import com.dashboard.adp.api.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @author Yunfei
 */

public interface RoleService {

    List<SysRole> getRoles(String uid);


    Integer count(Map<String, Object> params);

    List<SysRole> list(Map<String, Object> params, Integer offset, Integer limit);

    void saveRole(RoleDto roleDto);

    SysRole getSysRoleById(Long id);

    List<SysRole> roleAll();

    List<SysRole> listByUserId(Long id);

    Integer getRoleIdByUserIds(String userName);
}
