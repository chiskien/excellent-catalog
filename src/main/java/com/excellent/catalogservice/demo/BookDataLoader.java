package com.excellent.catalogservice.demo;

import com.excellent.catalogservice.domains.Book;
import com.excellent.catalogservice.repositories.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
//this class is instructed only if Profile testdata is active
//@ConditionalOnProperty(name = "polar.test-data.enabled", havingValue = "true")
@Profile("demo")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // test data is generated when an ApplicationReadyEvent is triggered
    @EventListener(ApplicationReadyEvent.class)
    public void loadBooks() {
        bookRepository.deleteAll();
        Instant now = Instant.now();
        Book b1 = Book.bookWithDate("1234567891", "Atomic Habits",
                "James Clear", 23.0, "Penguin", now, now);
        Book b2 = Book.bookWithDate("1234567899", "The Midnight Library",
                "Matt Haig", 200.0, "Penguin", now, now);
        bookRepository.saveAll(List.of(b1, b2));
    }
}
