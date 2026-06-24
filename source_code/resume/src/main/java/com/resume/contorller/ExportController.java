package com.resume.contorller;

import com.resume.pojo.User;
import com.resume.service.impl.WordExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ExportController {

    @Autowired
    private WordExportService wordExportService;

    @GetMapping("/export-word")
    public void exportToWord(HttpServletResponse response, HttpSession session,
                           @RequestParam(defaultValue = "pdf") String format) throws IOException {
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            wordExportService.exportUserToWord(user, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户未登录");
        }
    }
}
