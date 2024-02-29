package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.Martialart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MartialartRepository extends CrudRepository<Martialart, Integer> {

    //m als alias für martialart
    //gib martialart von martialart who martialart-name so ist ...'parameter'...
    @Query("SELECT m FROM Martialart m WHERE m.name LIKE CONCAT('%', :name, '%') ")
    Iterable<Martialart> findByName(@Param("name") String name);
    //@Param Notation weist den Wert des Methodenparameters 'name' der Query zu, wo der Parameter bei
    // ':name' eingesetzt wird


    //m als alias für martialart
    //gib martialart von martialart who martialart-HERKUNFT so ist ...'parameter'...
    @Query("Select m FROM Martialart m JOIN m.origin ori WHERE ori.name LIKE CONCAT ('%', :origin, '%')")
    Iterable<Martialart> findByOriginName(@Param("origin") String origin);
    //@Param Notation weist den Wert des Methodenparameters 'origin' der Query zu, wo der Parameter bei
    // ':origin' eingesetzt wird
}
