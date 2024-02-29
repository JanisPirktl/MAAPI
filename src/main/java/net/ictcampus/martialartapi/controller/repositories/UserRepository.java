package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends CrudRepository<User, Integer> {
}
