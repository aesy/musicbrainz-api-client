package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MusicBrainzSearchRequest<T> {

    @NotNull
    MusicBrainzSearchRequest<T> limitBy(long limit);

    @NotNull
    MusicBrainzSearchRequest<T> offsetBy(long offset);

    @NotNull
    MusicBrainzResponse<List<T>> search();

    @NotNull
    CompletableFuture<MusicBrainzResponse<List<T>>> searchAsync();

    void searchAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

    void searchChunksAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

}
