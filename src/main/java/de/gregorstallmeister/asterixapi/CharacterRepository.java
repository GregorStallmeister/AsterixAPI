package de.gregorstallmeister.asterixapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CharacterRepository extends MongoRepository<Character, String> {
    List<Character> findAllByAge(int age);
}
