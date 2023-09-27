package com.excellentbookshop.catalogservice.repositories;

import com.excellentbookshop.catalogservice.domains.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
