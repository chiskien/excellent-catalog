package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean existByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);


}
