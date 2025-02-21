package de.gregorstallmeister.asterixapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final AsterixService asterixService;

    public AsterixController(AsterixService asterixService) {
        this.asterixService = asterixService;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters(@RequestParam(required = false) Integer maxage) {
        return asterixService.findAllCharacters(maxage);
    }

    @PostMapping("/characters")
    public Character insertCharacter(@RequestBody NewCharacterDTO character) {
        return asterixService.insertCharacter(character);
    }

    @PutMapping("/characters")
    public Character updateCharacter(@RequestBody Character character) {
        return asterixService.updateCharacter(character);
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixService.deleteCharacterById(id);
    }

    @PostMapping("/pets")
    public Pet postPet(@RequestBody Pet pet) {
        return asterixService.insertPet(pet);
    }

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestParam (required = false) Boolean eatable) {
        return asterixService.findPets(eatable);
    }
}
