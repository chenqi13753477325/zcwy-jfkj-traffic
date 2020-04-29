package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dashboard.adp.api.model.SysPermission;
import com.dashboard.adp.api.model.SysUser;
import com.dashboard.adp.api.service.PermissionService;
import com.dashboard.adp.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Yunfei
 *
 */
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> getPerListByUid(Long userId) {
        return permissionMapper.getPerListByUid(userId);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return permissionMapper.count(params);
    }

    @Override
    public List<SysPermission> list(Map<String, Object> params, Integer offset, Integer limit) {
        return permissionMapper.list(params,offset,limit);
    }

    @Override
    public List<Map<String, Object>> listAll() {
        return permissionMapper.listAll();
    }

    @Override
    public List<Map<String, Object>> listAlls() {
        return permissionMapper.listAlls();
    }

    @Override
    public List<SysPermission> listPerByRoleId(Long roleId) {
        return permissionMapper.listPerByRoleId(roleId);
    }
}
