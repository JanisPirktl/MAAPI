package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.Martialart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MartialartRepository extends CrudRepository<Martialart, Integer> {
    @Query("SELECT m FROM Martialart m WHERE m.name LIKE CONCAT('%', :name, '%') ")
    Iterable<Martialart> findByName(@Param("name") String name);


}
