# MusicBrainz API Client

[![maven-central][maven-central-image]][maven-central-url]
[![Build Status][github-actions-image]][github-actions-url]
[![Coverage Status][codecov-image]][codecov-url]
[![MIT license][license-image]][license-url]

[maven-central-image]: https://img.shields.io/maven-central/v/io.aesy/musicbrainz-api-client?style=flat-square
[maven-central-url]: https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.aesy%22%20musicbrainz-api-client

[github-actions-image]: https://img.shields.io/github/workflow/status/aesy/musicbrainz-api-client/Continous%20Integration?style=flat-square
[github-actions-url]: https://github.com/aesy/musicbrainz-api-client/actions

[codecov-image]: https://img.shields.io/codecov/c/github/aesy/musicbrainz-api-client?style=flat-square
[codecov-url]: https://codecov.io/github/aesy/musicbrainz-api-client

[license-image]: https://img.shields.io/github/license/aesy/musicbrainz-api-client?style=flat-square
[license-url]: https://github.com/aesy/musicbrainz-api-client/blob/master/LICENSE

A complete Java 8+ wrapper library for [MusicBrainz](https://musicbrainz.org/) web service API 
(version 2).

### [API Reference](https://aesy.github.io/musicbrainz-api-client/)

## Usage
First you need to create an instance of a MusicBrainzApiClient. 
`MusicBrainzApiClient.createWithDefaults()` will create a new instance with a default HTTP client. 
You can provide your own client by using the static inner builder inside MusicBrainzApiClient. 

### Client examples:

#### Default client:

```java
MusicBrainzApiClient client = MusicBrainzApiClient.createWithDefaults();
```

All methods on a `MusicBrainzApiClient` return a `MusicBrainzRequest`. No actual HTTP request is 
sent though until one of two methods is called on this request. These methods are: 
`MusicBrainzRequest#execute` and `MusicBrainzRequest#executeAsync`. Like the names suggest, the 
first one is synchronous, while the second is asynchronous. The asynchronous method may take 
a callback/listener, otherwise it will return a `CompletableFuture`.

### Request examples:

#### Syncronous request:

```java
List<Artist> artists = client.artist.search("Peter Gabriel") // Create a search request.
                                    .execute()    // Execute the HTTP request synchronously and returns a `MusicBrainzResponse`.
                                    .getOrNull()  // Get a mapped entity of the response body (in this case an 
                                                  // `ArtistList` or null in case an error occurred. 
                                    .getArtist(); // Get list of artist entities.
System.out.println(artists.size()); 
```

#### Asynchronous request with callback:

```java
client.artist.search("Peter Gabriel") // Create a search request.
             // Execute the request asynchronously and provide a callback.
             .executeAsync(new MusicBrainzRequestCallbackAdapter<ArtistList>() {
                 // Only called on success, meaning that `MusicBrainzResponse#get` is guaranteed not to throw.
                 @Override
                 public void onSuccess(@NotNull MusicBrainzResponse.Success<ArtistList> response) {
                     List<Artist> artists = response.get().getArtist();
                     System.out.println(artists.size());
                 }
             });
```

#### Asynchronous request with CompletableFuture:

```java
client.artist.search("Peter Gabriel") // Create a search request.
             .executeAsync()          // Execute the request asynchronously and return a Future.
             .thenApply(response -> response.get().getArtist().size()) // Get the amount of artists.
             .thenAccept(System.out::println);                         // Print the result if no error occurred.
```

#### Response entities

All response entities are auto-generated from musicbrainz source. There are no guaranteed expected
values will be present, such as the artist list in the examples above. Make sure to check 
results for null.

## Installation
Using Gradle, add this to your build script: 

```groovy
repositories {
    mavenCentral()
}
dependencies {
    compile 'org.aesy:musicbrainz-api-client-core:1.0.0'
}
```

Using Maven, add this to your list of dependencies in `pom.xml`:

```xml
<dependency>
  <groupId>org.aesy</groupId>
  <artifactId>musicbrainz-api-client-core</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Contribute
Use the [issue tracker](https://github.com/aesy/musicbrainz-api-client/issues) to report bugs or 
make feature requests. Pull requests are welcome, but it may be a good idea to create an issue to 
discuss any changes beforehand.

## License
MIT, see [LICENSE](/LICENSE) file.