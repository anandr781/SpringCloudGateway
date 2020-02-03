# SpringCloudGateway
A minimal API Gateway implementation with Spring Cloud. 

### API Gateway concerns are as follows : 

* High Performance / Non-Blocking (NIO) serving of request
* Load balancing
* HTTP routing
* Security
* Rate limiting
* Observability - Metrics, monitoring, distributed logging, distributed tracing
* Connection queuing
* Circuit-breaking
* Authentication
* Device Detection

### API Gateway: Exposes your services as managed APIs (IMPORTANT : API Gateway is an Edge service)
* The key objective of using API Gateway is to expose your (micro) services as __managed APIs__. So, the API or Edge services that we develop at the API Gateway layer serves a specific business functionality.
* API/Edge services call the downstream (composite and atomic) microservices and contain the business logic that creates compositions/mashups of multiple downstream services.
* API/Edge services also need to call the downstream services on the resilient manner and apply various stability patterns such as __Circuit Breakers, Timeouts, Load Balancing/Failover__. Therefore most of the API Gateway solutions out there have these features built in.
* API Gateways also come inbuilt support for __service discovery, analytics(observability: Metrics, monitoring, distributed logging, distributed tracing.) and security__.
* API Gateways closely work with several other components of the API Management ecosystem, such as __API marketplace/store, API publishing portal__.

### Service Mesh (IMPORTANT : Service Mesh is a service-to-service communication and hence simpler) 
* Now let’s look at how we can differentiate Service Mesh.
* Service Mesh is a __network communication infrastructure which allows your to decouple and offload most of the application network functions from your service code.__
* Hence when you do __service-to-service communication__, you don’t need to implement resilient communication patterns such as Circuit breakers, timeouts in your service’s code. Similarly, service mesh provides other functionalities such as service discovery, observability etc.

### API Gateway and Service Mesh in Action
_The key differentiators between API Gateways and service mesh is that API Gateways is a key part of exposing API/Edge services where service mesh is merely an inter-service communication infrastructure which doesn’t have any business notion of your solution._

### The current implementation 
 * AbstractRoutePredicateFactory -> Base class which when extended provides predicate functionality for interception
 * AbstractGatewayFilterFactory -> Base class for all GatewayFilterFactory , provides a GatewayFilter to the upstream pipeline (ex., pipes and filters)
 * A CustomRewritePathGatewayFilterFactory -> Custom implementation of a RewritePathGatewayFilterFactory
