package org.example.ss08.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.ss08.controller.PersonController;

public class EmailExistValidator implements ConstraintValidator<EmailExist,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // false giữ lại và thông báo lỗi
        // true sẽ cho đi qua

        boolean isExist = PersonController.personList
                .stream()
                .anyMatch(p -> p.getEmail().equals(s));

        return !isExist;
    }
}
