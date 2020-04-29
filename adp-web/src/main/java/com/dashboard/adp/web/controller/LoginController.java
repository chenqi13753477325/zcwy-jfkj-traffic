package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dashboard.adp.api.model.SysPermission;
import com.dashboard.adp.api.service.PermissionService;
import com.dashboard.adp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * login controller
 * 2019/2/28
 */
@Controller
public class LoginController {

    /*User user = new User();*/

    @Reference
    UserService userService;
    @Reference
    PermissionService permissionService;

    /*//登录Controller
    @RequestMapping("/userlogin")
    public String login(@RequestParam(required = false) String id, String pass, Model m,  HttpSession session){
        Object usero = session.getAttribute("user");
        //如果用户登陆过直接进入用户主页
        if(usero!=null){
            user = (User)usero;
            User userById = userService.getUserById(user.getId());
            String nameStr = userById.getName();
            m.addAttribute("name",nameStr);
            List<SysPermission> list = permissionService.findPer(user.getId());
            return "index";
        }

        user.setId(id);
        user.setPassword(pass);

        //判断是否存在该用户
        int issu =  userService.hasUser(id,pass);
        if (issu==1){          //登录成功
            session.setAttribute("user",user);
            m.addAttribute("name",userService.getUserById(id).getName());
            List<SysPermission> list = permissionService.findPer(user.getId());
            return "index";
        }else if(issu==2){      //密码错误
            m.addAttribute("name","1");
            return "redirect:/login?name=1";
        }else {                 //不存在该用户
            m.addAttribute("name","2");
            return "redirect:/login?name=2";
        }
    }*/

    //跳转到登录页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String indexpage(String name,Model model){
        model.addAttribute("name",name);
        return  "login";
    }


    @RequestMapping("/")
    public String indexPage() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "index";
    }


    @RequestMapping("/index.html")
    public ModelAndView userUpdate(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 跳转到首页管理
     * @return
     */
    @RequestMapping("/homePage.html")
    public ModelAndView homePage(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("homePage");
        return mv;
    }



}