package org.example.ss08.controller;

import jakarta.validation.Valid;
import org.example.ss08.dto.PersonDTO;
import org.example.ss08.model.Gender;
import org.example.ss08.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
                            Gender.FEMALE,
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

    @GetMapping("/view-edit/{id}")
    public String viewEdit(
            @PathVariable(name = "id") Long editId,
            Model model
    ) {
        // 1. Tìm được cái đối tượng cần sửa
        Person person = personList.stream()
                .filter(p -> p.getId().equals(editId))
                .findFirst()
                .orElse(null);

        model.addAttribute("personDTO", person);
        return "person-form-edit";
    }

    @PostMapping("handle-update")
    public String handleUpdate(
            @ModelAttribute(name = "personDTO") PersonDTO personDTO
    ) {

        for (Person p : personList) {
            if (p.getId().equals(personDTO.getId())) {
                p.setName(personDTO.getName());
                p.setGender(personDTO.getGender());
                p.setDateOfBirth(personDTO.getDateOfBirth());
                p.setAge(personDTO.getAge());
                p.setEmail(personDTO.getEmail());
                break;
            }
        }

        return "redirect:/";
    }

}
