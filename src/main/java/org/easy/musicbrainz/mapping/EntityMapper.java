package org.easy.musicbrainz.mapping;

import org.easy.musicbrainz.exception.MusicBrainzMappingException;
import org.jetbrains.annotations.NotNull;

public interface EntityMapper {
    @NotNull <T> T deserialize(@NotNull String input, @NotNull Class<T> clazz) throws MusicBrainzMappingException;
    // @NotNull <T> String serialize(@NotNull T entity) throws MusicBrainzMappingException;
}
