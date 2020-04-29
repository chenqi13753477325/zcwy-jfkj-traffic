package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dashboard.adp.api.model.RoleDto;
import com.dashboard.adp.api.model.SysRole;
import com.dashboard.adp.api.service.RoleService;
import com.dashboard.adp.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * 2019/2/27
 *
 */
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<SysRole> getRoles(String uid) {
        return roleMapper.getRoles(uid);
    }



    @Override
    public Integer count(Map<String, Object> params) {
        return roleMapper.count(params);
    }


    @Override
    public List<SysRole> list(Map<String, Object> params, Integer offset, Integer limit) {
        return roleMapper.list(params,offset,limit);
    }

    @Override
    public void saveRole(RoleDto roleDto) {
        SysRole role = roleDto;
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);

        if (role.getId() != null) {// 修改
            updateRole(role, permissionIds);
        } else {// 新增
            saveRole(role, permissionIds);
        }
    }


    private void saveRole(SysRole role, List<Long> permissionIds) {
        SysRole r = roleMapper.getRole(role.getName());
        if (r != null) {
            throw new IllegalArgumentException(role.getName() + "已存在");
        }
        roleMapper.save(role);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleMapper.saveRolePermission(role.getId(), permissionIds);
        }
    }

    private void updateRole(SysRole role, List<Long> permissionIds) {
        SysRole r = roleMapper.getRole(role.getName());
        if (r != null && r.getId() != role.getId()) {
            throw new IllegalArgumentException(role.getName() + "已存在");
        }

        roleMapper.update(role);

        roleMapper.deleteRolePermission(role.getId());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleMapper.saveRolePermission(role.getId(), permissionIds);
        }
    }

    @Override
    public SysRole getSysRoleById(Long id) {
        return roleMapper.getSysRoleById(id);
    }

    @Override
    public List<SysRole> roleAll() {
        return roleMapper.roleAll();
    }

    @Override
    public List<SysRole> listByUserId(Long id) {
        List<SysRole> sysRoleLists = roleMapper.listByUserId(id);
        for(SysRole sysRole : sysRoleLists){
            if(sysRole.getLevel() == 1){
                //系统配置员
                sysRole.setName("Administrator");
            }else if(sysRole.getLevel() == 2){
                //事业部领导
                sysRole.setName("BusinessManager");
            }else if(sysRole.getLevel() == 3){
                //项目管理层
                sysRole.setName("ProjectManager");
            }
        }
        return sysRoleLists;
    }

    @Override
    public Integer getRoleIdByUserIds(String userName) {
        return roleMapper.getRoleIdByUserIds(userName);
    }
}
