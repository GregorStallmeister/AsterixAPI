package de.gregorstallmeister.asterixapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PetRepository extends MongoRepository<Pet, String> {

    List<Pet> findByEatableEquals(Boolean eatable);
}
