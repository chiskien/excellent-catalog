package com.excellentbookshop.catalogservice.services;

import com.excellentbookshop.catalogservice.exceptions.BookNotFoundException;
import com.excellentbookshop.catalogservice.models.Book;
import com.excellentbookshop.catalogservice.exceptions.BookAlreadyExistException;
import com.excellentbookshop.catalogservice.repositories.BookRepository;
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

    public Book addBooktoCatalog(Book book) {
        if (bookRepository.existByIsbn(book.isbn())) {
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBook(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    Book bookToUpdate = new Book(
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price()
                    );
                    return bookRepository.save(bookToUpdate);
                }).orElseGet(() -> addBooktoCatalog(book));
    }
}
