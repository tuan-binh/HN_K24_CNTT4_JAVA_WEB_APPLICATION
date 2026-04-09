package org.example.ss05.controller;

import org.example.ss05.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    // Hiển thị danh sách sản phẩm ra màn hình
    /**
     * Class Product
     * - id
     * - name
     * - price
     * - stock
     * - status
     *
     */

    List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product(1, "Nguyễn Trí Thắng", 5000, 1, true),
                    new Product(2, "Vũ Việt Tiến", 3600, 1, true),
                    new Product(3, "Hoàng Thái Minh", 1800, 1, false),
                    new Product(4, "Nguyễn Doanh Tuấn", 500, 1, true)
            )
    );

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("shop", products);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String handleDelete(
            @PathVariable(name = "id") int id
    ) {
        products = products.stream().filter(p -> p.getId() != id).toList();
        // cách 1: gửi thêm dữ liệu đi chính là shop và products
//        return "home";
        // cách 2: sử dụng redirect - điều hướng đến đúng cái đường dẫn nào có dấu /home
        return "redirect:/home";
    }

}
