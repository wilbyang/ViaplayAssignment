

## Programming Assignment
Create a REST API for providing clients with information about a specific music
artist. The information should be collected from 3 different sources: MusicBrainz, Cover Art
Archive and one source of your choice given from the MusicBrainz response.

### What I have implemented

1. a endpoint for query by mbid

2. a cache layer, with 4 cache bins with different TTL for caching the result from the 3rd party service


3. defensive programming, when data can not be retrieved from 3rd party service, return a default NOT_FOUND instance

4. unified error handling using @ControllerAdvice

5. unified response with code, message, data

6. test cases for unit test and integration test

### tools & frameworks I used

1. lombok, to reduce verbose codes for java entity
2. spring boot
3. redis as the cache layer


### How to run this implementation
1. You need to have a redis-server running, and then configure it in application.properties 
2. go to repo root and run ```mvn spring-boot:run```


### performance considerations

1. Cache the result from 3rd party service as much as possible, setting the TTL to be 10m



### future work & improvements
1. use authentication & authorization when consuming the API

2. use multithreading & thread pooling when issuing multiple http requests

3. use Kubernetes for deployment

4. use spring boot cloud for service monitoring, tracing, etc.


