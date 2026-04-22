package re.ss13.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    // Khai báo khoá chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

//    @Column(name = "age")
    // Nếu không đặt tên cột tự động sẽ lấy tên biến làm tên cột
    private Integer age;

    @ManyToMany
    // TỰ sinh ra bảng phụ
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Tạo 1 cái quan hệ 1 - 1
     * Tạo 1 cái quan hệ 1 - n
     * Toạ 1 cái quan hệ n - n
     *      - n - n những bảng phụ chỉ có 2 khoá chính
     *      - n - n nhưng bảng phụ có 1 thuộc tính khác nữa
     *      - n - n nhưng bảng phụ có khoá chính phức hợp (composite key)
     * */
}
