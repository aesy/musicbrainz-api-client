package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.ArtistList;
import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzArtistSearchRequestImpl
    extends MusicBrainzSearchRequestImpl<Artist>
    implements MusicBrainzArtistSearchRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final BooleanQuery.Builder queryBuilder;

    /* package-private */ MusicBrainzArtistSearchRequestImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        super(executor);

        this.target = target;
        this.queryBuilder = new BooleanQuery.Builder();
    }

    /* package-private */ MusicBrainzArtistSearchRequestImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor,
        @NotNull Query query
    ) {
        this(target, executor);

        this.queryBuilder.add(query, BooleanClause.Occur.MUST);
    }

    @NotNull
    @Override
    public MusicBrainzArtistSearchRequest withAlias(@NotNull String alias) {
        PhraseQuery query = new PhraseQuery("alias", alias);

        queryBuilder.add(query, BooleanClause.Occur.MUST);

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzArtistSearchRequest withArea(@NotNull String area) {
        PhraseQuery query = new PhraseQuery("area", area);

        queryBuilder.add(query, BooleanClause.Occur.MUST);

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzArtistSearchRequest withId(@NotNull UUID id) {
        PhraseQuery query = new PhraseQuery("arid", id.toString());

        queryBuilder.add(query, BooleanClause.Occur.MUST);

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzArtistSearchRequest withName(@NotNull String name) {
        PhraseQuery query = new PhraseQuery("artist", name);

        queryBuilder.add(query, BooleanClause.Occur.MUST);

        return this;
    }

    @Override
    protected @NotNull Response sendRequest(long limit, long offset)
        throws MusicBrainzNetworkException {

        BooleanQuery query = queryBuilder.build();

        if (query.clauses().isEmpty()) {
            throw new MusicBrainzNetworkException("Query must not be empty");
        }

        Invocation invocation = target
            .queryParam("query", query.toString())
            .queryParam("limit", limit)
            .queryParam("offset", offset)
            .request()
            .accept(MediaType.APPLICATION_XML)
            .buildGet();

        try {
            return invocation.invoke();
        } catch (ProcessingException exception) {
            throw new MusicBrainzNetworkException(exception);
        }
    }

    @NotNull
    @Override
    protected List<Artist> mapResponse(@NotNull Response response)
        throws MusicBrainzEntityMappingException {

        Metadata metadata;

        try {
            metadata = response.readEntity(Metadata.class);
        } catch (ProcessingException exception) {
            throw new MusicBrainzEntityMappingException(exception);
        }

        if (metadata == null) {
            throw new MusicBrainzEntityMappingException("Metadata is null");
        }

        ArtistList artistList = metadata.getArtistList();

        if (artistList == null) {
            throw new MusicBrainzEntityMappingException("Artist list is null");
        }

        List<Artist> artist = artistList.getArtist();

        if (artist == null) {
            throw new MusicBrainzEntityMappingException("Artist list is null");
        }

        return artist;
    }

}
