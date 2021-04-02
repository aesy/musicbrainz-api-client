<img align="left" width="80" height="80" src="./img/icon.svg">

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

A Java 8+ wrapper library for [MusicBrainz](https://musicbrainz.org/) web service API (version 2).

### [API Reference](https://aesy.github.io/musicbrainz-api-client/)

## Usage

First you need to create an instance of a MusicBrainzClient. 
The following will create a new musicbrainz client with a Jersey backend: 

```java
MusicBrainzClient client = MusicBrainzJerseyClient.createWithDefaults();
```

### Lookup requests

#### Syncronous request:

```java
Artist artist = client.artist()
        .withId(UUID.fromString("8e66ea2b-b57b-47d9-8df0-df4630aeb8e5"))
        .lookup()
        .get(); 
System.out.println(artist);
```

#### Asynchronous request with callback:

```java
client.artist()
        .withId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .lookupAsync(new MusicBrainzRequestCallbackAdapter<Artist>() {
            @Override
            public void onSuccess(@NotNull Artist artist) {
                System.out.println(artist);
            }
        });
```

#### Asynchronous request with CompletableFuture:

```java
client.artist()
        .withId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .lookupAsync()
        .thenApply(MusicBrainzResponse::get)
        .thenAccept(System.out::println);
```

### Browse requests

#### Syncronous request:

```java
List<Artist> artists = client.artist()
        .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .browse()
        .get(); 
System.out.println(artists.size());
```

#### Asynchronous request with callback:

```java
client.artist()
        .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .browseAsync(new MusicBrainzRequestCallbackAdapter<List<Artist>>() {
            @Override
            public void onSuccess(@NotNull List<Artist> artists) {
                System.out.println(artists.size());
            }
        });
```

#### Asynchronous chunked requests with callback:

Chunked requests periodically fetch chunks of data until all data that's available have been fetched.
The callback is invoked one or more times.

```java
client.artist()
        .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .browseChunksAsync(new MusicBrainzRequestCallbackAdapter<List<Artist>>() {
            @Override
            public void onSuccess(@NotNull List<Artist> artists) {
                System.out.println(artists.size());
            }
        });
```

#### Asynchronous request with CompletableFuture:

```java
client.artist()
        .withAreaId(UUID.fromString("1127ddc2-eab3-4662-8718-6adbdeea3b10"))
        .browseAsync()
        .thenApply(MusicBrainzResponse::get)
        .thenApply(List::size)
        .thenAccept(System.out::println);
```

### Search requests

Not yet implemented

### Response entities

All response entities are auto-generated from musicbrainz source. There is no guarantee any 
of the entities properties will be present, therefore make sure to check the results or 
handle nulls.

## Installation

The library has not yet been released to maven central

## Development

#### Prerequisites

* [A Java 8+ Runtime](https://adoptopenjdk.net/)
* [Maven 3.6+](https://maven.apache.org/download.cgi)

#### Build

To compile and package the application, simply issue the following command:

```sh
$ mvn package -DskipTests
```

This will create a jar located in `jersey-client/target/`.

#### Test 

This project uses [checkstyle](https://checkstyle.sourceforge.io/) for linting. Run it by using:

```sh
$ mvn checkstyle:check
```

All code that goes into master must pass all tests, including the lint checks.

To run unit tests, simply run:

```sh
$ mvn test
```

It's also possible to run these very same tests in integration test mode towards an existsing 
musicbrainz server by providing a url as an environment variable. The following command will 
run the tests towards the musicbrainz test server:

```sh
$ MUSICBRAINZ_URL=https://test.musicbrainz.org/ws/2 mvn test
```

These integration tests are slow because they use rate limiting as to not overload their servers. 

## Contribute
Use the [issue tracker](https://github.com/aesy/musicbrainz-api-client/issues) to report bugs or 
make feature requests. Pull requests are welcome, but it may be a good idea to create an issue to 
discuss any changes beforehand.

## License
MIT, see [LICENSE](/LICENSE) file.
