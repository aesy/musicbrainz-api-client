package org.easy.musicbrainz.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface ApiResponse<T> {
    int getStatusCode();
    boolean isSuccessful();
    // TODO map?
    @NotNull T get();
    @NotNull T getOr(@NotNull T other);
    @NotNull T getOr(@NotNull Supplier<T> supplier);
    @Nullable T getOrNull();
    @NotNull <E extends Throwable> T getOrThrow(@NotNull E throwable) throws E;
    @NotNull <E extends Throwable> T getOrThrow(@NotNull Supplier<E> supplier) throws E;
}
