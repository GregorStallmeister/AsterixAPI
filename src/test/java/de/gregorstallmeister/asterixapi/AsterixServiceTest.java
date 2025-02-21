package de.gregorstallmeister.asterixapi;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AsterixServiceTest {

    CharacterRepository mockCharacterRepository = mock(CharacterRepository.class);
    PetRepository mockPetRepository = mock(PetRepository.class);
    IdService mockIdService = mock(IdService.class);

    AsterixService asterixService = new AsterixService(mockCharacterRepository, mockPetRepository, mockIdService);

    @Test
    void findAllCharacters() {
        // given
        Character character1 = new Character("0", "Testix", 53, "Tester");
        Character character2 = new Character("1", "Mockix", 35, "Tester");
        when(mockCharacterRepository.findAll()).thenReturn(List.of(character1, character2));

        // when
        List<Character> actual = asterixService.findAllCharacters(null);

        // then
        assertEquals(List.of(character1, character2), actual);
//        verify(mockCharacterRepository.findAll());
    }

    @Test
    void findByID() {
        // given
        when(mockIdService.randomId()).thenReturn("42");
        String id = mockIdService.randomId();
        Character character = new Character(id, "Mockix", 35, "Tester");
        when(mockCharacterRepository.findById(id)).thenReturn(Optional.of(character));

        // when
        Optional<Character> actual = asterixService.findCharacterById(id);

        // then
        assertTrue(actual.isPresent());
        assertEquals(character, actual.get());
//        verify(mockIdService.randomId());
//        verify(mockCharacterRepository.findById(id));
    }

    @Test
    void findByIdNothingFound() {
        // given
        String id = "41";
        when(mockCharacterRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Optional<Character> actual = asterixService.findCharacterById(id);

        // then
        assertTrue(actual.isEmpty());
//        verify(mockCharacterRepository.findById(id));
    }
}
