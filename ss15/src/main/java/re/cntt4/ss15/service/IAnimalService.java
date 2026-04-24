package re.cntt4.ss15.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import re.cntt4.ss15.model.Animal;

import java.util.List;

public interface IAnimalService {

    Page<Animal> getAllAnimal(String search, Pageable pageable);

}
