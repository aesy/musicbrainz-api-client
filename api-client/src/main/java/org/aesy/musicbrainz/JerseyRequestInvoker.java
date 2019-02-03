package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/* package-private */ class JerseyRequestInvoker
    implements MusicBrainzRequestInvoker<Response> {

    @NotNull
    private final Invocation invocation;

    /* package-private */ JerseyRequestInvoker(
        @NotNull Invocation invocation
    ) {
        this.invocation = invocation;
    }

    @NotNull
    @Override
    public Response invoke()
        throws MusicBrainzNetworkException {

        try {
            return invocation.invoke();
        } catch (ProcessingException exception) {
            throw new MusicBrainzNetworkException(exception);
        }
    }

}
