package de.gregorstallmeister.asterixapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final CharacterRepository characterRepository;

    public AsterixController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters () {

        return characterRepository.findAll();
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
}
