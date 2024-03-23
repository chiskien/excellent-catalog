package com.excellent.catalogservice.repositories;

import com.excellent.catalogservice.config.SecurityConfig;
import com.excellent.catalogservice.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerMvcTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtDecoder jwtDecoder;


    @Test
    void whenDeleteBookWithzz

}
