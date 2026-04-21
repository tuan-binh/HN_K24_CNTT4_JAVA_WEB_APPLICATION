package org.example.ss12.repository;

import org.example.ss12.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository {

    List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1L, "Nguyễn Tiến Minh", 19, false, "Hoa Thanh Quế"),
                    new Student(2L, "Nguyễn Thị Phương", 19, true, "Hà Nội"),
                    new Student(3L, "Nguyễn Thị Kim Lệ", 20, true, "Thái Bình")
            )
    );

    public List<Student> findAll() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

}
