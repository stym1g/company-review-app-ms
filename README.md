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


