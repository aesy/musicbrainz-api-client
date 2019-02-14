package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MusicBrainzBrowseRequest<T> {

    @NotNull
    MusicBrainzBrowseRequest<T> limitBy(long limit);

    @NotNull
    MusicBrainzBrowseRequest<T> offsetBy(long offset);

    @NotNull
    MusicBrainzResponse<List<T>> browse();

    @NotNull
    CompletableFuture<MusicBrainzResponse<List<T>>> browseAsync();

    @NotNull
    void browseAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

    @NotNull
    void browseChunksAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

}
