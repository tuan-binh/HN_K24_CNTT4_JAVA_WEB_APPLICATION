package org.example.ss08.controller;

import jakarta.validation.Valid;
import org.example.ss08.dto.PersonDTO;
import org.example.ss08.model.Gender;
import org.example.ss08.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class PersonController {

    public static List<Person> personList = new ArrayList<>(
            Arrays.asList(
                    new Person(
                            1L,
                            "Trần Việt Nam",
                            Gender.MALE,
                            LocalDate.of(2006, 8, 6),
                            19,
                            "tranvietnam@gmail.com"
                    )
            )
    );

    @GetMapping
    public String home(Model model) {
        model.addAttribute("persons", personList);
        return "person-list";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        // Gửi sang view 1 dữ liệu rỗng
        model.addAttribute("personDTO", new PersonDTO());
        return "person-form";
    }

    // Thêm @Valid để kịch hoạt validation
    // Và đối tượng BindingResult để kiểm tra có lỗi hay không
    @PostMapping("/handle-submit")
    public String handleSubmit(
            @Valid @ModelAttribute(name = "personDTO") PersonDTO personDTO,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            // Gửi lại dữ liệu cũ khi bị lỗi
            model.addAttribute("personDTO", personDTO);
            return "person-form";
        }

        // chuyển đổi từ DTO -> Model (Entity)
        Person newPerson = new Person(
                personDTO.getId(),
                personDTO.getName(),
                personDTO.getGender(),
                personDTO.getDateOfBirth(),
                personDTO.getAge(),
                personDTO.getEmail()
        );
        personList.add(newPerson);
        return "redirect:/";
    }

}
