package com.excellent.catalogservice.repositories;

import com.excellent.catalogservice.domains.Genre;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Modifying
    @Transactional
    @Query("delete from genre where id = :id;")
    void deleteById(Long id);
}
