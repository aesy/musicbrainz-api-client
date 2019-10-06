package org.aesy.musicbrainz.concurrent;

import org.aesy.musicbrainz.util.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitedExecutorUnitTest
    extends UnitTest {

    @Test
    @DisplayName("Rate limiting")
    public void rate_limit() throws InterruptedException {
        Executor executor = new RateLimitedExecutor(10, TimeUnit.SECONDS);
        AtomicInteger integer = new AtomicInteger(0);

        for (int i = 0; i < 20; i++) {
            executor.execute(integer::getAndIncrement);
        }

        TimeUnit.SECONDS.sleep(1);

        assertThat(integer)
            // ±1 leeway
            .hasValueBetween(9, 11);
    }

}
