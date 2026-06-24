package com.resume.contorller;

import com.resume.pojo.User;
import com.resume.service.UserService;
import com.resume.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String adminPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            return "redirect:/login.html";
        }
    }

    @PostMapping("/admin/update")
    public String updateUser(User user, String password, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            // 如果密码字段不为空，则更新密码
            if (password != null && !password.isEmpty()) {
                user.setPassword(MD5Utils.code(password));
            } else {
                // 否则保持原密码
                user.setPassword(loginUser.getPassword());
            }
            user.setId(loginUser.getId());
            user.setUsername(loginUser.getUsername()); // 保持用户名不变
            userService.updateUser(user);
            // 更新session中的用户信息
            session.setAttribute("loginUser", user);
        }
        return "redirect:/admin";
    }
}
