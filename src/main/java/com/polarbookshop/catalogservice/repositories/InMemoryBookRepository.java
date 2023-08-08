package com.polarbookshop.catalogservice.repositories;

import com.polarbookshop.catalogservice.models.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private static final Map<String, Book> bookDictionary = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return bookDictionary.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public boolean existByIsbn(String isbn) {
        return false;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void deleteByIsbn(String isbn) {

    }
}
