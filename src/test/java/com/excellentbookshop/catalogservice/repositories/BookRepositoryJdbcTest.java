package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.config.DataConfig;
import com.excellentbookshop.catalogservice.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJdbcTest //identifies test class that focus on Spring Data JDBC components
@Import(DataConfig.class) //Import data config (need for enable auditing)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
) //Disables the default behavior of relying on an embedded test database since we want to use Testcontainers
@ActiveProfiles("integration")
class BookRepositoryJdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567899";
        var book = Book.of(bookIsbn, "Title", "Author", 124.5);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findBookByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}