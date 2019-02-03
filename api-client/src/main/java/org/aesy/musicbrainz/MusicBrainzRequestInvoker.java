package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
/* package-private*/ interface MusicBrainzRequestInvoker<T> {

    @NotNull
    T invoke()
        throws MusicBrainzNetworkException;

}
