package com.excellent.catalogservice.repositories;

import com.excellent.catalogservice.domains.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findBookByIsbn(String isbn); //the method will be providing at Runtim
    boolean existsBookByIsbn(String isbn);
    @Modifying
    @Transactional
    @Query("delete from book where isbn = :isbn;")
    void deleteByIsbn(String isbn);
}
