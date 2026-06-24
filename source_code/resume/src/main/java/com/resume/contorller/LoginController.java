package com.resume.contorller;


import com.resume.pojo.User;
import com.resume.service.UserService;
import com.resume.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

//登录
@Controller
public class LoginController {
    @Autowired
    private UserService userservice;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        RedirectAttributes attributes,
                        HttpSession session){
        User user = userservice.selectByUsernameAndPassword(username, MD5Utils.code(password));
        if(user != null ){
            session.setAttribute("loginUser",user);
            attributes.addFlashAttribute("users",user);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或者密码错误");
            return "login";
        }
    }

    @GetMapping("/main.html")
    public String mainPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser"); // 从 session 中获取用户信息
        if (user != null) {
            model.addAttribute("users",user);
            return "index"; // 返回主页面
        } else {
            return "redirect:/login.html"; // 用户未登录，重定向到登录页
        }
    }

    //退出
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
