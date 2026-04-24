package re.cntt4.ss15.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.cntt4.ss15.dto.AnimalDTO;
import re.cntt4.ss15.model.Animal;

import java.util.List;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal,Long> {

    @Query("SELECT new re.cntt4.ss15.dto.AnimalDTO(a.name,a.age) FROM Animal a")
    Page<AnimalDTO> findAllDTOProjection(Pageable pageable);



    // Hành động + By + Thuộc tính + Điều kiện
    Page<Animal> findAllByNameContains(String search, Pageable pageable);

    @Query("""
        SELECT a FROM Animal a WHERE a.name LIKE concat('%',:search,'%')
    """)
    // Nếu như mọi người mà muốn tự viết query cập nhật hay thêm mới hay xoá
    // Thì phải thêm 1 annotation là @Modifying
    List<Animal> searchAll(@Param("search") String search);



}