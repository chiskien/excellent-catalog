package com.excellentbookshop.catalogservice.services;

import com.excellentbookshop.catalogservice.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
