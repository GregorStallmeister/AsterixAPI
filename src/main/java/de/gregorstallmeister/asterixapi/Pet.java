package de.gregorstallmeister.asterixapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Pet(
        @Id
        String id,
        String species,
        String name,
        boolean eatable
) {
}
