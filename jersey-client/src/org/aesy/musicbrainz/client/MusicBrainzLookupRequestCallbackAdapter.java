package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

/**
 * Convenience adapter class which implements {@code MusicBrainzLookupRequest.Callback} with empty
 * method bodies.
 *
 * @param <T> The entity type of the response
 * @see MusicBrainzLookupRequest.Callback
 */
public abstract class MusicBrainzLookupRequestCallbackAdapter<T>
    implements MusicBrainzLookupRequest.Callback<T> {

    @Override
    public void onSuccess(@NotNull MusicBrainzResponse.Success<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onFailure(@NotNull MusicBrainzResponse.Failure<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onError(@NotNull MusicBrainzResponse.Error<T> exception) {
        // Intentionally left empty
    }

}
