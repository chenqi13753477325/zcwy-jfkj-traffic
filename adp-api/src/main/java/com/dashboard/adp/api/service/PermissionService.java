package com.dashboard.adp.api.service;

import com.dashboard.adp.api.model.SysPermission;

import java.util.List;
import java.util.Map;

/**
 * @author Yunfei
 *
 */
public interface PermissionService {

    List<SysPermission> getPerListByUid(Long id);

    Integer count(Map<String, Object> params);

    List<SysPermission> list(Map<String, Object> params, Integer offset, Integer limit);

    List<Map<String, Object>> listAll();

    List<Map<String, Object>> listAlls();

    List<SysPermission> listPerByRoleId(Long roleId);

}
