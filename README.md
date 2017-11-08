# MusicBrainz API Client

[![Maven Repo](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/org/aesy/musicbrainz/musicbrainz/maven-metadata.xml.svg)]()
[![Travis](https://img.shields.io/travis/aesy/musicbrainz-api-client.svg)](https://travis-ci.org/aesy/musicbrainz-api-client)
[![License](https://img.shields.io/github/license/aesy/musicbrainz-api-client.svg)](https://github.com/aesy/musicbrainz-api-client/blob/master/LICENSE)

A complete Java 8+ wrapper library for [MusicBrainz](https://musicbrainz.org/) web service API (version 2).

### [API Reference](https://aesy.github.io/musicbrainz-api-client/)

## Usage
First you need to create an instance of a MusicBrainzApiClient. `new MusicBrainzApiClient()` will create a new instance
with a default HTTP client. You can provide your own client, such as [OkHttp](http://square.github.io/okhttp/) by using 
the static inner builder inside MusicBrainzApiClient. Clients must implement `HttpClient`. Some adapter classes are 
already available by adding additional dependencies (See [below](#optional-dependencies)).

### Client examples:

#### Default client:

```java
MusicBrainzApiClient musicBrainz = new MusicBrainzApiClient();
```

#### OkHttp client (requires `adapter-okhttp` dependency):

```java
OkHttpClient httpClient = new OkHttpClient.Builder()
                                          .readTimeout(5, TimeUnit.SECONDS)
                                          .build();

MusicBrainzApiClient musicBrainz = new MusicBrainzApiClient.Builder()
                                                           .setHttpClient(new OkHttpClientAdapter(httpClient))
                                                           .build();
```

All methods on a `MusicBrainzApiClient` return a `ApiRequest`. No actual HTTP request is sent though until one of two 
methods is called on this request. These methods are: `ApiRequest#execute` and `ApiRequest#executeAsync`. 
Like the names suggest, the first one is synchronous, while the second is asynchronous. The asynchronous method may take 
a callback/listener, or else it will return a `Future`.

### Request examples:

#### Syncronous request:

```java
List<Artist> artists = client.artist.search("Peter Gabriel") // Create a search request.
                                    .execute()   // Execute the HTTP request synchronously and returns a `ApiResponse`.
                                    .getOrNull() // Get a mapped entity of the response body (in this case an 
                                                 // `ArtistList` or null in case an error occurred. 
                                    .getItems(); // Get list of artist entities.
System.out.println(artists.size()); 
```

#### Asynchronous request with callback:

```java
client.artist.search("Peter Gabriel") // Create a search request.
             // Execute the request asynchronously and provide a callback.
             .executeAsync(new ApiRequestCallbackAdapter<ArtistList>() {
                 // Only called on success, meaning that `ApiResponse#get` is guaranteed not to throw.
                 @Override
                 public void onSuccess(@NotNull DefaultApiResponse.Success<ArtistList> response) {
                     List<Artist> artists = response.get().getItems();
                     System.out.println(artists.size());
                 }
             });
```

#### Asynchronous request with Future:

```java
client.artist.search("Peter Gabriel") // Create a search request.
             .executeAsync()          // Execute the request asynchronously and return a Future.
             .thenApply(response -> response.get().getItems().size()) // Get the amount of artists.
             .thenAccept(System.out::println);                        // Print the result if no error occurred.
```

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

## Optional dependencies

Some adapters are available for common HTTP clients.

OkHttp: `org.aesy:musicbrainz-api-client-adapter-okhttp` 

Apache HttpClient: `org.aesy:musicbrainz-api-client-adapter-apache` 


## Contribute
Use the [issue tracker](https://github.com/aesy/musicbrainz-api-client/issues) to report bugs or make feature requests.
Pull requests are welcome, but it may be a good idea to create an issue to discuss any changes beforehand.

## License
MIT, see [LICENSE](/LICENSE) file.