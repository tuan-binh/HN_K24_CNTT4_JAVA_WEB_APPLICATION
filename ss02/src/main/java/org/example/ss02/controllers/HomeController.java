package org.example.ss02.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// đánh dấu nó là controller
@Controller
// đánh dấu đường gốc URL (Ngõ)
@RequestMapping("/home") // nếu không ghi gì thì đường dẫn là /home
public class HomeController {

    // HTTP Method (GET, POST) đại diện cho ngách
    @GetMapping // Đại diện cho ngách và phương thức GET
    public String home(HttpServletRequest request) {
        List<String> names = new ArrayList<>(
                Arrays.asList(
                        "Vũ Việt Tiến",
                        "Nguyễn Doanh Tuấn",
                        "Hoàng Thái Minh",
                        "Phạm Thị Hồng Nhung"
                )
        );
        request.setAttribute("names", names);
        request.setAttribute("money",1200000);
        return "students";
    }

    @GetMapping("/add")
    public String formAdd() {
        return "form_add";
    }

    // Thay vì gửi đối tượng fix cứng trong Java
    // Chuyển đổi là sang tương tác database lấy từ database
    // Hiển thị dưới dạng bảng bên JSP + JSTL

    // Giải pháp:
    // Bước làm:
    /**
     * 1. Tạo class Person
     * 2. Tương tác với database
     *     - Lấy SELECT * FROM persons
     *     - Chuyển đổi thành đối tượng
     *     - Thêm vào danh sách List<Person>
     * 3. Lưu trữ danh sách đối tượng List
     * 4. Gửi dữ liệu sang JSP + JSTL
     *     - thông qua HttpServletRequest request.setAttribute()
     * 5. JSP:
     *     - Sử dụng c:forEach để hiển thị dưới dạng bảng
     * */

}
