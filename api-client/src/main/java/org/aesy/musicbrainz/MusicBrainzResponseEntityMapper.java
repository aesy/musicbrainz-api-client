package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.jetbrains.annotations.Nullable;

/**
 * Maps an response entity value to another value.
 *
 * @param <T> The input type
 * @param <U> The output type
 */
@FunctionalInterface
public interface MusicBrainzResponseEntityMapper<T, U> {

    /**
     * Maps an response entity value to another value.
     *
     * @param value The response entity value
     * @return The mapped value
     * @throws MusicBrainzEntityMappingException If value could not be mapped to another value
     */
    @Nullable
    U map(@Nullable T value)
        throws MusicBrainzEntityMappingException;

}
