package net.ictcampus.martialartapi.controller.repositories;

import net.ictcampus.martialartapi.model.models.Origin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends CrudRepository<Origin, Integer> {
}
