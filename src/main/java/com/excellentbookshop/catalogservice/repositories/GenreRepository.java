package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.models.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository {

    Optional<Genre> findGenreById(Genre genre);
}
