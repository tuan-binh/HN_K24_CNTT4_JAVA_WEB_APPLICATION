package org.example.ss08.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // Tạo ra bài đọc để người dùng có thể hiểu
// Khi kịch hoạt validation sẽ gọi đến lớp kiểm tra
@Constraint(validatedBy = EmailExistValidator.class)
// Kiểm tra trong quá trình chạy (Runtime)
@Retention(RetentionPolicy.RUNTIME)
// mục đích validate ở thuộc tính hay phương thức
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface EmailExist {
    // Nếu người dùng sử dụng annotation này mà không ghi message thì sẽ lấy giá trị mặc định
    String message() default "Email đã tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
