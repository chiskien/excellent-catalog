package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository {

    Optional<Genre> findGenreById(Genre genre);
}
