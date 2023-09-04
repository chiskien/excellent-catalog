package com.excellentbookshop.catalogservice.services;

import com.excellentbookshop.catalogservice.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {


    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private final GenreRepository genreRepository;
}
