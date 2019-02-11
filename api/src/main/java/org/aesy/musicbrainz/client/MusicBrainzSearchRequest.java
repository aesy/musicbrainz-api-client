package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MusicBrainzSearchRequest<T> {

    @NotNull
    MusicBrainzResponse<List<T>> search();

    @NotNull
    CompletableFuture<MusicBrainzResponse<List<T>>> searchAsync();

    @NotNull
    void searchAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

}
