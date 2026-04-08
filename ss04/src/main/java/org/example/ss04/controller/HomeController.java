package org.example.ss04.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ss04.dao.StudentDAO;
import org.example.ss04.model.Student;
import org.example.ss04.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Đánh dấu bean nó là controller
@Controller
// Đánh dấu đây là đường dẫn đến controller
// Giống như người giúp việc làm được nhiều điều
@RequestMapping // mặc định là dấu /

// Các annotation phố biến:
/**
 *      Các annotation không gì bean dùng để khởi tạo và sử dụng DI
 *      - @Component: Đánh dấu là bean để khởi tạo và ko có ngữ nghĩa
 *
 *      - @Controller: Đánh dấu là bean và mục đích là điều hướng (lễ tân)
 *
 *      - @Service: Đánh dấu là bean và mục đích là nghiệp vụ
 *
 *      - @Repository: Đánh dấu là bean và mục đích xử lý tương tác database (DAO)
 *
 *      - @Autowired: Dùng để tiêm sự phụ thuộc (DI - Dependency Injection)
 * */

public class HomeController {

    // DI
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDAO studentDAO;

    // Chức năng 1 có đường dẫn gì
    // Đại diện cho 1 tính năng của người giúp việc (rửa bát)

    /**
     * biến của @RequestMapping
     * - @GetMapping: đại diện cho phương thức GET - cần tập trung
     * - @PostMapping: đại diện cho phương thức POST - cần tập trung
     * - @PutMapping: đại diện cho phương thức PUT
     * - @PatchMapping: đại diện cho phương thức PATCH
     * - @DeleteMapping: đại diện cho phương thức DELETE
     */

//    @RequestMapping(method = RequestMethod.GET) - cách cũ
    @GetMapping // - cách mới
    public String home(Model model) {
        List<Student> students = studentService.getAllStudent();
        System.out.println(students);

        model.addAttribute("listStudent", students);

        return "home";
    }

    @GetMapping("/search")
    public String search(
            // name đại diện cho key của tham số (có thể tự đặt)
            // defaultValue đại diện cho giá trị mặc định khi nó không gửi giá trị
            @RequestParam(name = "full_name", defaultValue = "") String keyword,
            Model model) {
        System.out.println("nội dung: " + keyword);
        // Bây giờ làm sao để tìm kiếm
        List<Student> students = studentDAO.search(keyword);
        model.addAttribute("listStudent",students);
        return "home";
    }

    // Thường là những trang detail (chi tiết)
    // id ở đường dẫn có thể đặt theo tuỳ biến
    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable int id
    ) {
        System.out.println("Nội dung giá trị trên đường dẫn: " + id);
        return "home";
    }

}
