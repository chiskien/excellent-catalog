package com.excellentbookshop.catalogservice.domains;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {
    private static Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Book b = Book.of("1234567890", "Title", "Author", 9.90,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(b);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book b = Book.of("1234567890", "Title", "", 9.90,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(b);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The author must be defined");

    }
}