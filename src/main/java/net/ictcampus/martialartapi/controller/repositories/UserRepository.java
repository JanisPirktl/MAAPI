package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(@Param("name") String username);
    //@Param Notation weist den Wert des Methodenparameters 'username' der Query zu, wo der Parameter bei
    // ':name' eingesetzt wird
}
