package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Event;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzEventBrowseRequest
    extends MusicBrainzBrowseRequest<Event> {

    @NotNull
    MusicBrainzEventBrowseRequest include(@NotNull String... include);

}

