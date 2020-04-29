package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.model.SysPermission;
import com.dashboard.adp.api.service.PermissionService;
import com.dashboard.adp.web.page.table.PageTableHandler;
import com.dashboard.adp.web.page.table.PageTableRequest;
import com.dashboard.adp.web.page.table.PageTableResponse;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {

    @Reference
    PermissionService permissionService;

    /**
     * 跳转到菜单管理
     * @return
     */
    @RequestMapping("/menu_list.html")
    public ModelAndView menuList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("menu/menu_list");
        return mv;
    }



    @RequestMapping("/listMenu")
    public PageTableResponse listMenu() {
        PageTableRequest request = new PageTableRequest();

        PageTableResponse handle = new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return permissionService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<SysPermission> list(PageTableRequest request) {
                List<SysPermission> list = permissionService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);

        return handle;
    }




    @RequestMapping("/listMenuAll")
    public Object listMenuAll() {
        List<Map<String, Object>> list = permissionService.listAll();
        com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
        setPermissionsTree(0, list, array);
        return array;
    }

    @RequestMapping("/listMenuAlls")
    public Object listMenuAlls() {
        List<Map<String, Object>> list = permissionService.listAlls();
        com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
        setPermissionsTree(0, list, array);
        return array;
    }


    @RequestMapping("/listPerByRoleId")
    @ResponseBody
    public Object listPerByRoleId(@RequestParam(value="roleId",required=false) String roleId) {
        List<SysPermission> sysPermissions = permissionService.listPerByRoleId(Long.valueOf(roleId));
        Long[] age = new Long[sysPermissions.size()];
        for(int i=0;i<sysPermissions.size();i++){
            age[i] = sysPermissions.get(i).getId();
        }
        return age;
    }

    private void setPermissionsTree(Integer pId, List<Map<String, Object>> permissionsAll, com.alibaba.fastjson.JSONArray array) {
        for (Map<String, Object> per : permissionsAll) {
            Integer pid = (Integer) per.get("pid");
            if (pid.equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);

                if (permissionsAll.stream().filter(p -> p.get("pid").equals(per.get("id"))).findAny() != null) {
                    com.alibaba.fastjson.JSONArray child = new com.alibaba.fastjson.JSONArray();
                    parent.put("child", child);
                    setPermissionsTree((Integer) per.get("id"), permissionsAll, child);
                }
            }
        }
    }


}
