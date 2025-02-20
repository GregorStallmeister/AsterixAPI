package de.gregorstallmeister.asterixapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final CharacterRepository characterRepository;
    private final PetRepository petRepository;

    public AsterixController(CharacterRepository characterRepository, PetRepository petRepository) {
        this.characterRepository = characterRepository;
        this.petRepository = petRepository;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters(@RequestParam(required = false) Integer age) {

        if (age == null)
            return characterRepository.findAll();
        else
            return characterRepository.findAllByAge(age);
    }

    @PostMapping("/characters")
    public Character postCharacter(@RequestBody Character character) {
        characterRepository.insert(character);

        return character;
    }

    @PutMapping("/characters")
    public Character updateCharacter(@RequestBody Character character) {
        characterRepository.save(character);

        return character;
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id) {
        characterRepository.deleteById(id);
    }

    @PostMapping("/pets")
    public Pet postPet(@RequestBody Pet pet) {
        petRepository.insert(pet);

        return pet;
    }

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestParam (required = false) Boolean eatable) {
        if (eatable == null)
            return petRepository.findAll();
        else
            return petRepository.findByEatableEquals(eatable);
    }
}
