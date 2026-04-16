package org.example.ss09.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/theme")
public class ThemeController {

    // chức năng set giá trị theme
    @GetMapping("/set-theme")
    public String setTheme(
            HttpServletResponse response,
            @RequestParam(name = "theme", defaultValue = "light") String mode
    ) {
        // Tạo được cookie
        Cookie cookie = new Cookie("theme", mode);
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30 ngày

        // Thêm cookie vào trình duyệt
        response.addCookie(cookie);

        return "home";
    }


    // chức năng đọc giá trị theme trên cookie
    @GetMapping("/read-cookie")
    public String readCookie(
            @CookieValue(name = "theme", defaultValue = "light") String mode
    ) {
        System.out.println("Theme hiện tại: " + mode);
        return "home";
    }


    // chức năng xoá cookie
    @GetMapping("/delete-cookie")
    public String deleteCookie(
            HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("theme", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "home";
    }
}
