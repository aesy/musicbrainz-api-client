package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Instrument;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzInstrumentBrowseRequest
    extends MusicBrainzBrowseRequest<Instrument> {

    @NotNull
    MusicBrainzInstrumentBrowseRequest include(@NotNull String... include);

}
