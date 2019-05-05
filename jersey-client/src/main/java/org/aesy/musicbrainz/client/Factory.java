package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
/* package-private */ interface Factory<T> {

    @NotNull
    T create();

}
