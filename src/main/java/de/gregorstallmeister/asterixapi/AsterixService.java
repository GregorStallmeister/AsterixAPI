package de.gregorstallmeister.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final CharacterRepository characterRepository;
    private final PetRepository petRepository;
    private final IdService idService;

    public List<Character> findAllCharacters (Integer maxAge) {
        if (maxAge == null)
            return characterRepository.findAll();
        else
            return characterRepository.findAll()
                    .stream()
                    .filter(character -> character.age() <= maxAge)
                    .toList();
    }

    public Character insertCharacter (CharacterNewDto characterNewDto) {
        String id = idService.randomId();
        Character insertCharacter = new Character(id, characterNewDto.name(), characterNewDto.age(), characterNewDto.profession());
        characterRepository.insert(insertCharacter);

        return insertCharacter;
    }

    public Character updateCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Optional<Character> findCharacterById (String id) {
        return characterRepository.findById(id);
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
