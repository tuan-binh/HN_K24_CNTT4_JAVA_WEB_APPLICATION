package org.example.ss09.controller;

import org.example.ss09.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
@SessionAttributes("dataLogin") // Cơ chế tự động lưu vào session khi trùng tên với dữ liệu gửi lên
public class AccountController {

    List<Account> accounts = new ArrayList<>(
            Arrays.asList(
                    new Account(1L, "Hoàng Thái Minh", "minhbeo", "123456"),
                    new Account(2L, "Phạm Tuấn Bình", "binh123456", "123456")
            )
    );

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        model.addAttribute("dataLogin", new Account());
        return "login-form";
    }

    @PostMapping("/handle-login")
    public String handleLogin(
            @ModelAttribute(name = "dataLogin") Account account,
            Model model
    ) {

        Account dataLogin = accounts.stream()
                .filter(
                        a -> a.getUsername().equals(account.getUsername())
                                && a.getPassword().equals(account.getPassword())
                ).findFirst()
                .orElse(null);

        if(dataLogin == null) {
            model.addAttribute("error","Tài khoản hoặc mật khẩu sai...");
            return "login-form";
        } else {
            System.out.println("Welcome: "+dataLogin.getFullName());
        }

        return "home";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "home";
    }

}
