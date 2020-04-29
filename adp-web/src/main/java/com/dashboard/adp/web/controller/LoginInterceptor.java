package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dashboard.adp.api.service.RoleService;
import com.dashboard.adp.web.config.JwtTokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;


@Controller
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger log = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Reference
    private RoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getRequestURI();
        if(path!=null && "/index.html".equals(path)){
            String tokenStr = request.getParameter("token");
            if(tokenStr!=null && !"".equals(tokenStr)){
                String usernameFromToken = jwtTokenUtil.getUsernameFromToken(tokenStr);
                if (usernameFromToken!=null){
                    Integer roleIdByUserIds = roleService.getRoleIdByUserIds(usernameFromToken);
                    if(roleIdByUserIds!=null && roleIdByUserIds == 1){
                        return true;
                    }
                }
            }else{
                response.sendError(401, "no key,reject !");
                return false;
            }
        }
        return true;
    }


    /**
     * 是否进行登陆过滤
     * @param path
     * @param basePath
     * @return
     */
    private boolean doLoginInterceptor(String path,String basePath){
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        //notLoginPaths.add("/");
        notLoginPaths.add("/index");
        if(notLoginPaths.contains(path)){
            return false;
        }
        return true;
    }

}
