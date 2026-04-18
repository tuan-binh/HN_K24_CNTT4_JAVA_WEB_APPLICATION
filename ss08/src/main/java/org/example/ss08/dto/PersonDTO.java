package org.example.ss08.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.example.ss08.model.Gender;
import org.example.ss08.validator.EmailExist;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

// Khi mọi người làm việc DB (id tự tăng) thì không cần nhập id nữa
public class PersonDTO {
    @Min(1)
    private Long id;

    @NotBlank(message = "Tên không được bỏ trống")
    private String name;

    private Gender gender;

    @Past(message = "Không được lớn hơn ngày hiện tại")
    // Khi làm việc với dữ liệu req với Date thì sẽ phải thêm Annotation DateTimeFormatter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Min(value = 18,message = "Phải trên hoặc bằng 18 tuổi")
    private Integer age;

    @Pattern(
            regexp = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}",
            message = "Email không đúng định dạng"
    )
    @EmailExist
    private String email;

    private MultipartFile avatar;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String name, Gender gender, LocalDate dateOfBirth, Integer age, String email, MultipartFile avatar) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
