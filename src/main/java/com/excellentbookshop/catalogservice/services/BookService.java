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
        return bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBooktoCatalog(Book book) {
        if (bookRepository.existsBookByIsbn(book.isbn())) {
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBook(String isbn, Book book) {
        return bookRepository.findBookByIsbn(isbn)
                .map(existingBook -> {
                    Book bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            book.publisher(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version()
                    );
                    return bookRepository.save(bookToUpdate);
                }).orElseGet(() -> addBooktoCatalog(book));
    }
}
