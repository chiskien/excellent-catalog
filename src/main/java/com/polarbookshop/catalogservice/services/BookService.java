package com.polarbookshop.catalogservice.services;

import com.polarbookshop.catalogservice.models.Book;
import com.polarbookshop.catalogservice.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetail(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

}
