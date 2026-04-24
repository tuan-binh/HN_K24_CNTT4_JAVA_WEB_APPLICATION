package re.cntt4.ss15.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import re.cntt4.ss15.dto.AnimalDTO;
import re.cntt4.ss15.model.Animal;
import re.cntt4.ss15.repository.IAnimalRepository;
import re.cntt4.ss15.service.IAnimalService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements IAnimalService {
    private final IAnimalRepository animalRepository;

    @Override
    public Page<Animal> getAllAnimal(String search, Pageable pageable) {
        // Thư viện data.domain
//        Pageable pageable = PageRequest.of(page, size);

        // Để phân trang được thì cần phải nhận về 1 danh sách
        // Page<Animal>

        Page<AnimalDTO> result = animalRepository.findAllDTOProjection(pageable);

        // Nếu rỗng
        if(search.isEmpty()) {
        return animalRepository.findAll(pageable);
        }
        return animalRepository.findAllByNameContains(search,pageable);
    }
}
