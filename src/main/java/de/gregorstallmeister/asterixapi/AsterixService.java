package de.gregorstallmeister.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final CharacterRepository characterRepository;
    private final PetRepository petRepository;
    private final IdService idService;

    public List<Character> findAllCharacters (Integer age) {
        if (age == null)
            return characterRepository.findAll();
        else
            return characterRepository.findAllByAge(age);
    }

    public Character insertCharacter (NewCharacterDTO newCharacterDTO) {
        String id = idService.randomId();
        Character insertCharacter = new Character(id, newCharacterDTO.name(), newCharacterDTO.age(), newCharacterDTO.profession());
        characterRepository.insert(insertCharacter);

        return insertCharacter;
    }

    public Character updateCharacter(Character character) {
        return characterRepository.save(character);
    }

    public void deleteCharacterById(String id) {
        characterRepository.deleteById(id);
    }

    public Pet insertPet(Pet pet) {
        return petRepository.insert(pet);
    }

    public List<Pet> findPets(Boolean eatable) {
        if (eatable == null)
            return petRepository.findAll();
        else
            return petRepository.findByEatableEquals(eatable);
    }

}
