package org.example.ss12.service;

import lombok.RequiredArgsConstructor;
import org.example.ss12.dto.StudentDTO;
import org.example.ss12.model.Student;
import org.example.ss12.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Tiêm thông qua constructors
public class StudentService {
    // Bắt buộc phải là final khi tiêm
    private final StudentRepository studentRepository;

//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void addStudent(StudentDTO studentDTO) {

        Student optional = studentRepository.findAll()
                .stream()
                .max(Comparator.comparingLong(Student::getId))
                .orElse(null);


        // Sử dụng builder để tạo đối tượng
        Student newStudent = Student.builder()
                .id(optional == null ? 1 : optional.getId() + 1)
                .fullName(studentDTO.getFullName())
                .age(studentDTO.getAge())
                .gender(studentDTO.getGender())
                .address(studentDTO.getAddress())
                .build();
        studentRepository.addStudent(newStudent);
    }


}
