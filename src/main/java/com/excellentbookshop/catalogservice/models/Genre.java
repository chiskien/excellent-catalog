package com.excellentbookshop.catalogservice.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.stereotype.Component;

import java.time.Instant;

public record Genre(
        @Id
        Long id,
        @NotBlank(message = "The name of the genre must not be empty")
        String name,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version
) {
    public static Genre of(String genre) {
        return new Genre(null, genre, null, null, 0);
    }
}
