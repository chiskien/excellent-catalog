package com.excellentbookshop.catalogservice.demo;

import com.excellentbookshop.catalogservice.models.Book;
import com.excellentbookshop.catalogservice.repositories.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
        Book b1 = new Book("1234567891", "Atomic Habits", "James Clear", 23.0);
        Book b2 = new Book("1234567899", "The Midnight Library", "Matt Haig", 200.0);
        bookRepository.save(b1);
        bookRepository.save(b2);
    }
}
