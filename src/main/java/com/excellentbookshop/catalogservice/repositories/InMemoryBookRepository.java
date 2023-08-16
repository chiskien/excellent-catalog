package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.models.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private static final Map<String, Book> bookMap = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return bookMap.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existByIsbn(isbn) ? Optional.of(bookMap.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existByIsbn(String isbn) {
        return bookMap.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        bookMap.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookMap.remove(isbn);
    }
}
