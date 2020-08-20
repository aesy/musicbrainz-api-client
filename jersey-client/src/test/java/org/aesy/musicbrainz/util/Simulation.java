package org.aesy.musicbrainz.util;

public interface Simulation
    extends AutoCloseable {

    void verify();

    @Override
    default void close() {
        verify();
    }

}
