Database running on port(tcp)- 5432
pgadmin url- localhost:8888
Zipkin running on localhost:9411

containers in use for this service or compose yml file through command- docker compose up -d   (docKer-compose.yml file)
postgres, dpage/pgadmin4, openzipkin/zipkin, rabbitmq
Check running images- docker ps
Stop a container forcefully- docker kill f34f68df66bf

Now we have 3 microservices running independently.
We there is a need to communicate these services to each other.
Two ways to implement communication between services i.e. inter-service communication-
1. Synchronous Communication- Uses synchronous request-response mechanism where client waits for the response from the server before proceeding and this can be achieve through http based protocols such as REST or SOAP.
2. Asynchronous Communication- microservices can works asynchronously and they can communicate with the help of message queue or message broker like RabbitMQ, Apache Kafka or AWS SQS. The sending service publishes message to a particular queue and receiving service will consume the message from the queue when it is ready.

Rest Templates- It is a class in spring framework that simplifies the http communication between two microservices.
You can handle http request and response through REST template to communicate microservices.

Advantages-
1. Abstraction- abstracts the complexity of http communication and provides simple method based API for client side http access.
2. Versatility
3. Conversion
4. Error Handling
5. Integration
REST Template blocking that means it waits for the service to responding. This could be disadvantage in case you want non-blocking.

DTO(Data transfer Object) Pattern-
Design pattern used to transfer data between applications.

Service Registry- Service registry in ms architecture is used to enable dynamic service discovery.
It is a kind of centralized server where every microservice register themself and whenever you need to communicate to other microservice, you do a service discovery request and then you communicate. You don't need to hardcode urls in microservices.

Benifits-
1. Dynamic service discovery.
2. Load Balancing.
3. Fault tolerance and Resilience.
4. Scalability and Elasticity.
5. Service monitoring and Health check.

Spring Cloud Netflix-
With a few simple annotations, you can quickly enable and configure the common patterns inside your application and build large distributed systems with battle-tested Netflix components. The patterns provided include Service Discovery (Eureka).

To create service registry, goto start.spring.io and generate a project by adding web and Eureka Server dependency.
Add @EnableEurekaServer annotation for application main class.

Eureka server url- localhost:8761

micrometer- for tracing microservices
Zipkin- checking traces and spans on ui
Zipkin url- localhost:9411

Best practices
Use consistent naming convention
Secure your zipkin server
distributed tracing comes with some performance impact

Configuration management
As number of configurations increases, managing individual configuration may become a complex task.
A centralized config server provides a central place for managing configurations across microservicesd.

Spring Cloud config server- part of Spring Cloud project, a suit of tools specially designed for building and managing cloud-native application.
Storing configurations
Serving configurations
Refreshing configurations
Easy integration with springboot
Support for different environments
Encryption Decryption

Create a git repo with application.properties file and add config there.
In config-server service add git repo uri to application.properties under spring.cloud.config.server.git.uri tag
Now, we can check config added in git repo with this url- http://192.168.222.190:8080/application/dev

Mark the config server as optional to avoid failure if config server is down.

API Gateway
A special type of reverse proxy or something that routes the requests from the client application to the appropriate microservice
and acts as a single entry point for the external client requests.
Advantages
Encapsulate internal system architecture.
Handle security, load balancing, rate limit and analytics.
Can authenticate incoming requests and pass only valid requests to the services.
Can aggregate responses from different microservices.

API Gateway functions-
Request routing,
Load balancing,
Authentication and authorization,
Rate limiting,
Request and response transformation,
Aggregate data from multiple services.


Need of Fault tolerance
Ability to continue operating without any interruption
Fault isolation
network latency
deployment issues
increased complexity
elasticity
Tolerating external failure


Resilience is ability or capacity to recover quickly from difficulties.
Techniques
1. Retries
2. Rate limiting
3. Bulkheads
4. Circuit breakers
5. Fallbacks
6. Timeouts
7. Graceful degradation

We can use Resilience4J library to implement some of these features
It is lightweight and easy to use library which is build for functional programing paradigm.
It has below some of main modules:
1. Retry module
2. Rate Limiter
3. Bulkhead
4. Circuit breaker

Rate Limiting- technique for limiting network traffic.
Importance-
1. Preventing abuse
2. Resource allocation
3. Cost management
Use cases of rate limiting
1. APIs
2. Web scraping
3. Logging attempts

DDoS(Distributed denial of service) attacks
Multiple systems flood bandwidth of a particular system and they try to take it down.

We can implement Rate limiting feature through Resilience4J in springboot.(Rate limiter module)


Message Queues- form of asynchronous service-to-service communication used in serverless and microservices architectures.
Need of Message Queues
Decoupling
Asynchronous communication
Scalability
Fault Tolerant
Event-Driven Architecture
Time Decoupling

Producer- create messages and adds to message queue
Consumer- process messages produced by producer
Ex.- RabbitMQ, Apache Kafka, AWS SQS

To use RabbitMQ through docker
run below command or add command in docker-composed file
# latest RabbitMQ 3.13
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
url for rabbitmq management- localhost:15672
Default username and password- guest, guest






