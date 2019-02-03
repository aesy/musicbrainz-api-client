package org.aesy.musicbrainz;

import org.jetbrains.annotations.NotNull;

/**
 * Convenience adapter class which implements {@code BasicMusicBrainzRequest.Callback} with empty
 * method bodies.
 *
 * @param <T> The entity type of the response
 * @see BasicMusicBrainzRequest.Callback
 */
public abstract class MusicBrainzRequestCallbackAdapter<T>
    implements BasicMusicBrainzRequest.Callback<T> {

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
