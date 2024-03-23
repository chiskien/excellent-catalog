package com.excellent.catalogservice.services;

import com.excellent.catalogservice.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {


    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private final GenreRepository genreRepository;
}
