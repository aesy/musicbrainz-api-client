package io.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

public interface MusicBrainzClient {

    @NotNull
    MusicBrainzAreaEndpoint area();

    @NotNull
    MusicBrainzArtistEndpoint artist();

    @NotNull
    MusicBrainzEventEndpoint event();

    @NotNull
    MusicBrainzInstrumentEndpoint instrument();

    @NotNull
    MusicBrainzLabelEndpoint label();

    @NotNull
    MusicBrainzPlaceEndpoint place();

    @NotNull
    MusicBrainzRecordingEndpoint recording();

    @NotNull
    MusicBrainzReleaseEndpoint release();

    @NotNull
    MusicBrainzReleaseGroupEndpoint releaseGroup();

    @NotNull
    MusicBrainzSeriesEndpoint series();

    @NotNull
    MusicBrainzWorkEndpoint work();

    @NotNull
    MusicBrainzUrlEndpoint url();

}
