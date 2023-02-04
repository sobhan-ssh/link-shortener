# LINK SHORTENER SERVICE

The goal was to develop a very simple application in order to support functionalities such converting long links to short URLs, retrieving original long URLs by their generated short URLs 
and having some analytical info like click counts per short links
<br/>
To achieve this goal I have created six service:
1. DISCOVERY-SERVER: each service will register itself here during the startup and can help us in monitoring active services and having load-balancing between multiple instances of a service
2. CONFIG-SERVER: this service will help us to externalize our sensitive projects configuration
3. API-GATEWAY: will cover the functionalities of implementation and route the incoming requests to the suitable service
4. SHORTENER-SERVICE: have only a single API and by sending long URLs will return a unique short URL and also will save it on Redis and produce an event for LINK-SAVER-SERVICE to persist it on database
5. LINK-SAVER-SERVICE: will consume SHORTENER-SERVICE events and persist short and long URL pairs in database, it also contains another consumer related to click ratio per short link
6. REDIRECT-SERVICE: contains an API that by sending the short URL will return the original one and another API to return the click count of specific short URL

<br/>
This project implemented with the following stack:
<ul>
<li>JAVA, as the base language of project</li>
<li>SPRING-BOOT, SPRING-CLOUD, SPRING-DATA, HIBERNATE as the main frameworks of the project</li>
<li>POSTGRES, main relational database</li>
<li>MAVEN, as the build tool and dependency manager for the project</li>
<li>REDIS, used for cache layer</li>
<li>APACHE KAFKA, Event streamer to have async communication between services</li>
<li>DOCKER, is used to run the project in a containerized environment</li>
</ul>

# HOW TO RUN PROJECT
**You just need to have `Docker` and `Maven` installed on your machine**

- There is also an export of API services for using in Postman application, this file exists in the root of project with name of **url-shortener-gateway.postman_collection.json**