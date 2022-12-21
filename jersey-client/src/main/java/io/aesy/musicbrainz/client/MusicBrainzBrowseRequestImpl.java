package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.exception.MusicBrainzClientException;
import io.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import io.aesy.musicbrainz.exception.MusicBrainzException;
import io.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* package-private */ abstract class MusicBrainzBrowseRequestImpl<T>
    implements MusicBrainzBrowseRequest<T> {

    @NotNull
    private final Executor executor;

    private long limit;

    private long offset;

    protected MusicBrainzBrowseRequestImpl(
        @NotNull Executor executor
    ) {
        this.executor = executor;
        this.limit = 25;
        this.offset = 0;
    }

    @NotNull
    @Override
    public MusicBrainzBrowseRequest<T> limitBy(long limit) {
        this.limit = limit;

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzBrowseRequest<T> offsetBy(long offset) {
        this.offset = offset;

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzResponse<List<T>> browse() {
        try {
            return browseAsync().get();
        } catch (InterruptedException | ExecutionException exception) {
            MusicBrainzException wrappedException = new MusicBrainzClientException(exception);

            return new MusicBrainzResponseImpl.Error<>(wrappedException);
        }
    }

    @NotNull
    @Override
    public CompletableFuture<MusicBrainzResponse<List<T>>> browseAsync() {
        return CompletableFuture.supplyAsync(this::doBrowse, executor);
    }

    @Override
    public void browseAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback) {
        browseAsync()
            .handleAsync(new MusicBrainzResponseCallbackHandler<>(callback));
    }

    @Override
    public void browseChunksAsync(@NotNull MusicBrainzRequestCallback<List<T>> callback) {
        CompletableFuture.runAsync(() -> doBrowserChunked(callback), executor);
    }

    @NotNull
    protected abstract Response sendRequest(long limit, long offset)
        throws MusicBrainzNetworkException;

    @NotNull
    protected abstract List<T> mapResponse(@NotNull Response response)
        throws MusicBrainzEntityMappingException;

    @NotNull
    private MusicBrainzResponse<List<T>> doBrowse() {
        List<T> result = new ArrayList<>();
        int statusCode = -1;

        Iterator<MusicBrainzResponse<List<T>>> iterator = new ResponseIterator(limit, offset);

        while (iterator.hasNext()) {
            MusicBrainzResponse<List<T>> response;

            try {
                if (result.isEmpty()) {
                    // Make sure the first api call is not unnecessarily rate limited since the
                    // call of this method already is.
                    response = iterator.next();
                } else {
                    response = CompletableFuture.supplyAsync(iterator::next, executor).get();
                }
            } catch (InterruptedException | ExecutionException exception) {
                MusicBrainzException wrappedException = new MusicBrainzClientException(exception);

                return new MusicBrainzResponseImpl.Error<>(wrappedException);
            }

            if (!response.isSuccessful()) {
                return response;
            }

            List<T> entities = response.get();
            statusCode = response.getStatusCode();

            result.addAll(entities);
        }

        return new MusicBrainzResponseImpl.Success<>(statusCode, result);
    }

    public void doBrowserChunked(@NotNull MusicBrainzRequestCallback<List<T>> callback) {
        MusicBrainzResponseCallbackHandler<List<T>> handler
            = new MusicBrainzResponseCallbackHandler<>(callback);
        Iterator<MusicBrainzResponse<List<T>>> iterator = new ResponseIterator(limit, offset);

        while (iterator.hasNext()) {
            MusicBrainzResponse<List<T>> response;

            try {
                response = CompletableFuture
                    .supplyAsync(iterator::next, executor)
                    .handleAsync(handler)
                    .get();
            } catch (InterruptedException | ExecutionException exception) {
                callback.onError(new MusicBrainzClientException(exception));

                return;
            }

            if (!response.isSuccessful()) {
                return;
            }
        }
    }

    private final class ResponseIterator
        implements Iterator<MusicBrainzResponse<List<T>>> {

        private static final long STEP_SIZE = 100;

        private final long maxLimit;

        private long current;

        private boolean hasNext;

        private ResponseIterator(
            long limit,
            long offset
        ) {
            this.maxLimit = limit;
            this.current = offset;
            this.hasNext = true;
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @NotNull
        @Override
        public MusicBrainzResponse<List<T>> next() {
            Response response;
            long limit;

            if (maxLimit <= 0) {
                limit = STEP_SIZE;
            } else {
                limit = Math.min(maxLimit - current, STEP_SIZE);
            }

            try {
                response = sendRequest(limit, current);
            } catch (MusicBrainzNetworkException exception) {
                return new MusicBrainzResponseImpl.Error<>(exception);
            }

            int statusCode = response.getStatus();

            if (statusCode < 200 || statusCode >= 300) {
                try {
                    String message = response.readEntity(String.class);

                    return new MusicBrainzResponseImpl.Failure<>(statusCode, message);
                } catch (ProcessingException exception) {
                    return new MusicBrainzResponseImpl.Failure<>(statusCode);
                }
            }

            List<T> entities;

            try {
                entities = mapResponse(response);
            } catch (MusicBrainzEntityMappingException exception) {
                return new MusicBrainzResponseImpl.Error<>(exception);
            }

            current += STEP_SIZE;

            if (entities.isEmpty()) {
                hasNext = false;
            }

            if (maxLimit > 0 && current >= maxLimit) {
                hasNext = false;
            }

            return new MusicBrainzResponseImpl.Success<>(statusCode, entities);
        }

    }

}
