package io.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

/* package-private */ final class JerseyClientUserAgentFilter
    implements ClientRequestFilter {

    @NotNull
    private final String userAgent;

    /* package-private */ JerseyClientUserAgentFilter(
        @NotNull String userAgent
    ) {
        this.userAgent = userAgent;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        requestContext.getHeaders()
                      .add(HttpHeaders.USER_AGENT, userAgent);
    }

}
