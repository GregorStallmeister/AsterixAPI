package de.gregorstallmeister.asterixapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AsterixControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CharacterRepository characterRepository;

    @Test
    @DirtiesContext
    void getCharacters() throws Exception {
        // given

        // when / then
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DirtiesContext
    void getCharactersWithGivenCharacter() throws Exception {
        // given
        Character character = new Character("1", "Asterix", 35, "Warrior");
        characterRepository.save(character);

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                         {
                          "id": "1",
                          "name": "Asterix",
                          "age": 35,
                          "profession": "Warrior"
                         }
                        ]
                        """));

    }

    @Test
    @DirtiesContext
    void getCharactersWithGivenCharacterWithParamMaxageExpectOne() throws Exception {
        // given
        Character character = new Character("1", "Asterix", 35, "Warrior");
        characterRepository.save(character);

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters?maxage=45"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                         {
                          "id": "1",
                          "name": "Asterix",
                          "age": 35,
                          "profession": "Warrior"
                         }
                        ]
                        """));

    }

}
