package org.easy.musicbrainz.http;

import org.jetbrains.annotations.Nullable;

public interface HttpResponse {
    int getStatusCode();
    boolean isSuccessful();
    @Nullable String getBody();
}
