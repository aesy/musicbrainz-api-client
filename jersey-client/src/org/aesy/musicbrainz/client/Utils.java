package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* package-private */ final class Utils {

    private Utils() {}

    @NotNull
    /* package-private */ static <T> T getOrDefault(@Nullable T first, @NotNull T second) {
        if (first != null) {
            return first;
        }

        return second;
    }

}
