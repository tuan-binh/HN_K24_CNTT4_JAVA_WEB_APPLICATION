package org.example.ss08.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// Wrapper Class
// Nguyên thuỷ đẩy lên kiểu đối tượng
// int --> Integer
// long --> Long

public class Person {
    private Long id;
    private String name;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Integer age;
    private String email;

    private String avatar;

    public Person() {
    }

    public Person(Long id, String name, Gender gender, LocalDate dateOfBirth, Integer age, String email, String avatar) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
