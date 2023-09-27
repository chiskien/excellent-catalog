package com.excellentbookshop.catalogservice;

import com.excellentbookshop.catalogservice.domains.Book;
import com.excellentbookshop.catalogservice.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class ExcellentBookShopApplicationTests {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetRequestWithIdThenBookReturned() {
        Random random = new Random();
        var bookIsbn = "1231231230";
        var bookToCreate = Book.of(bookIsbn, "Title", "Author",
                9.90, "Penguin");
        Book expectedBook = webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
                .returnResult().getResponseBody();

        webTestClient
                .get()
                .uri("/books/" + bookIsbn)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assert expectedBook != null;
                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
                });
        bookRepository.deleteByIsbn(bookIsbn);
    }

    @Test
    void whenPostRequestThenBookCreated() {
        var bookIsbn = "1231231231";
        var expectedBook = Book.of(bookIsbn,
                "Title", "Author", 9.90, "Penguin");
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
                });
        bookRepository.deleteByIsbn(bookIsbn);
    }

    @Test
    void whenPutRequestThenBookUpdated() {
        var bookIsbn = "1231231232";
        var bookToCreate = Book.of(bookIsbn, "Test Title", "Test author",
                9.90, "Penguin");
        Book createdBook = webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
                .returnResult().getResponseBody();
        assert createdBook != null;
        var bookToUpdate = new Book(createdBook.id(), createdBook.isbn(), createdBook.title(), createdBook.author(),
                7.95, "Penguin",
                createdBook.createdDate(), createdBook.lastModifiedDate(), createdBook.version());

        webTestClient
                .put()
                .uri("/books/" + bookIsbn)
                .bodyValue(bookToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.price()).isEqualTo(bookToUpdate.price());
                });
        bookRepository.deleteByIsbn(bookIsbn);
    }

    @Test
    void whenDeleteRequestThenBookDeleted() {
        var bookIsbn = "1231231233";
        var bookToCreate = Book.of(bookIsbn, "Test Title", "Test author",
                9.90, "Penguin");
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated();

        webTestClient
                .delete()
                .uri("/books/" + bookIsbn)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/books/" + bookIsbn)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).value(errorMessage ->
                        assertThat(errorMessage).isEqualTo("The book with ISBN: " + bookIsbn + " was not found")
                );

    }
}