package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
