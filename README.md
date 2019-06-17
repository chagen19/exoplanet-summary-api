# Exoplanet Summary API
Project demonstrating a bunch of API goodness using Spring Boot 2.    
Features include:
* Embedded Swagger 2 UI
* Tracing id header propagation and logging
* Annotation based client retry using spring-retry
* Prometheus metrics
* Structured logging to standard out
* Global exception handling through controller advice
* Custom health indicators
* Liveness/Readiness endpoints
* Access logging
* Project Lombock for value and data objects
* Kotlin, JUnit5, Spek2, Spring controller/client tests
* Distroless base Docker image
* Gradle build pluging for docker builds
* Docker and Kubernetes deployment methods

### Requirements  
* Java 12

**If you see this message on compile, you will need to update to Java 12**

I believe it's related to this bug here, which isn't fixed as of openjdk 11.0.3: https://bugs.openjdk.java.net/browse/JDK-8222754
```java
compiler message file broken: key=compiler.misc.msg.bug arguments=11.0.3, {1}, {2}, {3}, {4}, {5}, {6}, {7}
java.lang.NullPointerException

```
  
#### Clone GIT Projects for POC  
```bash  
git clone https://github.com/chagen19/exoplanet-summary-api.git
```

### Change to project directory
```bash
cd exoplanet-summary-api
```

### Run the service (Kubernetes) 
```bash
# Start minikube
minikube start
# deploy the manifest
kubectl apply -f manifest.yaml 
# tear down
kubectl delete -f manifest.yaml
```

#### Run the service (Docker) 
```bash
# Build the image locally (optional)
./gradlew build docker
# Start the container
docker run --rm -p 8080:8080 -t chagen19/exoplanet-summary-api:1.0.0
```
Docker Repo: https://cloud.docker.com/u/chagen19/repository/docker/chagen19/exoplanet-summary-api
  
#### Run the service (Gradle) 
```bash
./gradlew clean bootRun
```  

#### Run Tests  
```bash
./gradlew clean test
```
 
#### Test the app 
Note: for minikube substitute `$(minikube ip):30217` for `:8080`
*http $(minikube ip):30217/v1/exoplanetSummary*
```bash  
# Examples using HTTPie. Substitute your favorite REST Client  
http :8080/v1/exoplanetSummary/liveness  
http :8080/v1/exoplanetSummary/readiness  
# view configured properties and env variables  
http :8080/v1/env  
# view configured spring beans  
http :8080/v1/beans  
# retrieve exoplanets summary  
http :8080/v1/exoplanetSummary  
# view prometheus metrics  
http :8080/v1/prometheus  
```  
  
#### Swagger Docs  
http://localhost:8080/v1/swagger-ui.html  
