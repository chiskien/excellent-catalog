package com.excellentbookshop.catalogservice.controllers;

import com.excellentbookshop.catalogservice.exceptions.BookNotFoundException;
import com.excellentbookshop.catalogservice.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "12313213";
        given(bookService.viewBookDetail(isbn))
                .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}