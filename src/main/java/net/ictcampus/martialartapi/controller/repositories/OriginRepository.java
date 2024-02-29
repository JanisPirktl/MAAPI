package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.Origin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends CrudRepository<Origin, Integer> {

    //o = alias von Origin
    //Gib Origin von Origin wo Origin-Name so ist ...'Parameter'...
    @Query("SELECT o FROM Origin o WHERE o.name LIKE CONCAT('%', :name, '%') ")
    Iterable<Origin> findByName(@Param("name") String name);
    //@Param Notation weist den Wert des Methodenparameters 'name' der Query zu, wo der Parameter bei
    // ':name' eingesetzt wird
}
