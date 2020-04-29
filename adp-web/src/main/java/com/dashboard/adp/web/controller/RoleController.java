package com.dashboard.adp.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dashboard.adp.api.model.RoleDto;
import com.dashboard.adp.api.model.SysRole;
import com.dashboard.adp.api.service.RoleService;
import com.dashboard.adp.web.page.table.PageTableHandler;
import com.dashboard.adp.web.page.table.PageTableRequest;
import com.dashboard.adp.web.page.table.PageTableResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  2019/2/28
 */
@RestController
public class RoleController {

    @Reference
    RoleService roleService;

    @RequestMapping("/role")
    public String toRoleHtml() {
        return "/role/Role";
    }
    @RequestMapping("/role/changerole")
    public String changeRole(Model m, @RequestParam String uid) {
        List<SysRole> roles = roleService.getRoles(uid);
        m.addAttribute("roles",roles);
        m.addAttribute("changid",uid);
        return "/role/changerole";
    }



    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping("/role_list.html")
    public ModelAndView adminList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("role/role_list");
        return mv;
    }


    @RequestMapping("/listRoles")
    @ResponseBody
    public PageTableResponse listRoles(HttpServletRequest requests,@RequestParam(value="name",required=false) String name) {
        PageTableRequest request = new PageTableRequest();
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        request.setParams(map);
        request.setLimit(Integer.valueOf(requests.getParameter("length")));
        request.setOffset(Integer.valueOf(requests.getParameter("start")));
        PageTableResponse handle = new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return roleService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<SysRole> list(PageTableRequest request) {
                List<SysRole> list = roleService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);

        return handle;
    }




    /**
     * 跳转到添加角色
     * @return
     */
    @GetMapping("/role_add.html")
    public ModelAndView adminAdd(@RequestParam(value="id",required=false) String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("id",id);
        mv.setViewName("role/role_add");
        return mv;
    }


    @PostMapping("/saveRoles")
    public void saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
    }

    @RequestMapping("/getSysRole")
    @ResponseBody
    public SysRole getSysRole(@RequestParam(value="id",required=false) String id) {
        return roleService.getSysRoleById(Long.valueOf(id));
    }

    @RequestMapping("/roleAll")
    public List<SysRole> roleAll() {
        return roleService.roleAll();
    }

    @RequestMapping("/getRoleByUserId")
    public List<SysRole> getRoleByUserId(@RequestParam(value="userId",required=false) String userId) {
        List<SysRole> sysRoles = roleService.listByUserId(Long.valueOf(userId));
        return sysRoles;
    }



}
