package org.aesy.musicbrainz;

// import org.aesy.musicbrainz.client.MusicBrainzClient;
// import org.aesy.musicbrainz.client.MusicBrainzJerseyClient;
// import org.aesy.musicbrainz.client.MusicBrainzRequestCallbackAdapter;
// import org.aesy.musicbrainz.entity.Artist;
// import org.aesy.musicbrainz.exception.MusicBrainzException;
// import org.jetbrains.annotations.NotNull;
//
// import java.util.List;
// import java.util.UUID;
//
// public class Main {
//     private static final MusicBrainzClient musicBrainz;
//
//     static {
//         musicBrainz = MusicBrainzJerseyClient.builder()
//             .baseUrl(MusicBrainzJerseyClient.MUSICBRAINZ_API_TEST_URL)
//             .build();
//     }
//
//     public static void main(String[] args)
//         throws Exception {
//
//         for (int i = 1; i < 10; i++) {
//             makeQueryAsync(i);
//             System.out.println("async done");
//         }
//
//         makeQuerySync(0);
//
//         System.out.println("sync done");
//
//         musicBrainz.artist()
//             .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
//             .limitBy(200)
//             .browseChunksAsync(new MusicBrainzRequestCallbackAdapter<List<Artist>>() {
//                 @Override
//                 public void onSuccess(@NotNull List<Artist> entity) {
//                     System.out.println("Success");
//                 }
//
//                 @Override
//                 public void onFailure(int statusCode, @NotNull String message) {
//                     System.out.println("Failure");
//                     System.out.println(statusCode);
//                     System.out.println(message);
//                 }
//
//                 @Override
//                 public void onError(@NotNull MusicBrainzException exception) {
//                     System.out.println("Error");
//                     System.out.println(exception.getMessage());
//                 }
//             });
//
//         System.out.println("browse done");
//
//         // musicBrainz.artist()
//         //     .query()
//         //     .text("Woop")
//         //     .inArea("Stockholm")
//         //     .searchAsync()
//         //     .thenApply(MusicBrainzResponse::get)
//         //     .thenApply(List::size)
//         //     .thenAccept(System.out::println);
//
//         // musicBrainz.artist()
//         //     .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
//         //     .limitBy(1)
//         //     .browseAsync()
//         //     .thenApply(MusicBrainzResponse::get)
//         //     .thenApply(List::size)
//         //     .thenAccept(System.out::println);
//
//         List<Artist> artists = musicBrainz.artist()
//             .query()
//             .withArea("sweden")
//             .withName("kent")
//             .search()
//             .getOrNull();
//
//         System.out.println(artists);
//
//         Thread.sleep(20000);
//     }
//
//     private static void makeQueryAsync(int i) {
//         long start = System.currentTimeMillis();
//
//         musicBrainz.artist()
//             .withId(UUID.fromString("8e66ea2b-b57b-47d9-8df0-df4630aeb8e5"))
//             .lookupAsync(new MusicBrainzRequestCallbackAdapter<Artist>() {
//                 @Override
//                 public void onSuccess(@NotNull Artist artist) {
//                     long end = System.currentTimeMillis();
//                     long runtime = end - start;
//                     System.out.println("Asynchronous request " + i + " took " + runtime + "ms");
//                     // System.out.println(artist.getGender().getContent());
//                 }
//             });
//     }
//
//     private static void makeQuerySync(int i) {
//         long start = System.currentTimeMillis();
//
//         Artist artist = musicBrainz.artist()
//             .withId(UUID.fromString("8e66ea2b-b57b-47d9-8df0-df4630aeb8e5"))
//             .lookup()
//             .get();
//
//         long end = System.currentTimeMillis();
//         long runtime = end - start;
//         System.out.println("Synchronous request " + i + " took " + runtime + "ms");
//         // System.out.println(artist.getGender().getContent());
//     }
//
// }
