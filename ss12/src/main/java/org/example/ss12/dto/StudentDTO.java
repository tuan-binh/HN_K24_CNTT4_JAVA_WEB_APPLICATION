package org.example.ss12.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

// Constructors không tham số
@NoArgsConstructor
// Constructors đầy đủ tham số
@AllArgsConstructor
// Sinh ra getter tương ứng với các thuộc tính
@Getter
// Sinh ra setter tương ứng với các thuộc tính
@Setter
// Tạo ra đối tượng dưới dạng tượng tự như stream api
@Builder
public class StudentDTO {
    @NotBlank(message = "Tên không được để trống")
    private String fullName;
    @NotNull(message = "Tuổi không được để trống")
    @Min(18)
    private Integer age;
    @NotNull(message = "Giới tính không được bỏ trống")
    private Boolean gender;
    @NotBlank(message = "Địa chỉ không được bổ trống")
    private String address;
}
