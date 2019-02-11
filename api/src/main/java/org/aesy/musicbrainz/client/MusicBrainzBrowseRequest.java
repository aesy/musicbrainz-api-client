package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MusicBrainzBrowseRequest<T> {

    @NotNull
    MusicBrainzResponse<List<T>> browse(long limit);

    @NotNull
    MusicBrainzResponse<List<T>> browse(long limit, long offset);

    @NotNull
    CompletableFuture<MusicBrainzResponse<List<T>>> browseAsync(long limit);

    @NotNull
    void browseAsync(long limit, @NotNull MusicBrainzRequestCallback<List<T>> callback);

    @NotNull
    CompletableFuture<MusicBrainzResponse<List<T>>> browseAsync(long limit, long offset);

    @NotNull
    void browseAsync(long limit, long offset, @NotNull MusicBrainzRequestCallback<List<T>> callback);

    @NotNull
    MusicBrainzResponse<List<T>> browseAll();

    @NotNull
    void browseAllAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback);

}
