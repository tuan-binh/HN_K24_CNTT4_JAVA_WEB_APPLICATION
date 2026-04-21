package org.example.ss12.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ss12.dto.StudentDTO;
import org.example.ss12.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final StudentService studentService;

    // Phương thức trả về view home.html
    @GetMapping
    public String home(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "home";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "form";
    }

    @PostMapping("/handle-submit")
    public String handleSubmit(
            @Valid @ModelAttribute(name = "studentDTO") StudentDTO studentDTO,
            BindingResult result,
            Model model
    ) {
        // Validate
        if (result.hasErrors()) {
            model.addAttribute("studentDTO", studentDTO);
            return "form";
        }

        studentService.addStudent(studentDTO);

        return "redirect:/";
    }

}
