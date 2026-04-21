package org.example.ss12.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    private Long id;
    private String fullName;
    private Integer age;
    private Boolean gender;
    private String address;
}
