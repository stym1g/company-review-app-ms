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

