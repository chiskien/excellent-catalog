package com.excellent.catalogservice.repositories;

import com.excellent.catalogservice.domains.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository {
    private static final Map<String, Book> bookMap = new ConcurrentHashMap<>();


    public Iterable<Book> findAll() {
        return bookMap.values();
    }


    public Optional<Book> findByIsbn(String isbn) {
        return existByIsbn(isbn) ? Optional.of(bookMap.get(isbn)) : Optional.empty();
    }


    public boolean existByIsbn(String isbn) {
        return bookMap.get(isbn) != null;
    }


    public Book save(Book book) {
        bookMap.put(book.isbn(), book);
        return book;
    }


    public void deleteByIsbn(String isbn) {
        bookMap.remove(isbn);
    }
}
