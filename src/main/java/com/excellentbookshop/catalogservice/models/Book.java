package com.excellentbookshop.catalogservice.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;


//
public record Book(
        @Id Long id,

        @CreatedDate
        Instant createDate,

        @LastModifiedDate
        Instant lastModifiedDate,
        @NotBlank(message = "The Book ISBN must be defined")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13}$)",
                message = "The ISBN format must be valid")
        String isbn,
        @NotBlank(message = "The book title must be defined")
        String title,
        @NotBlank(message = "The author must be defined")
        String author,
        @Positive(message = "The book price must be greater than zero")
        Double price,
        @Version int version) {
    public static Book of(String isbn, String title, String author, Double price) {
        return new Book(null, null, null,
                isbn, title, author, price, 0);
    }
}
