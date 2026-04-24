package re.cntt4.ss15.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import re.cntt4.ss15.service.IAnimalService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AnimalController {
    private final IAnimalService animalService;

    @GetMapping
    public String home(
//            @RequestParam(name = "page",defaultValue = "0") Integer page,
//            @RequestParam(name = "size",defaultValue = "3") Integer size,
            @PageableDefault(page = 0,size = 3,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "search",defaultValue = "") String search,
            Model model
    ) {

        // Nhưng thông tin của Page<Animal> nó trả về rất nhiều
        /**
         * Gồm:
         *      content: danh sách động vật
         *      totalElements: Tổng số phần tử
         *      totalPages: Tổng số trang
         *      current: là trang hiện tại
         * */
        model.addAttribute(
                "animals",
                animalService.getAllAnimal(search,pageable));
        return "zoo";
    }

}
