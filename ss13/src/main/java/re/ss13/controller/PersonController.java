package re.ss13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.ss13.model.Person;
import re.ss13.repository.IPersonRepository;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PersonController {
    // Đang áp dụng DI và Nguyên lý SOLID - D
    private final IPersonRepository personRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("persons", personRepository.getAll());
        return "person-list";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute(name = "person") Person person
    ) {
        personRepository.save(person);
        return "redirect:/";
    }


}
