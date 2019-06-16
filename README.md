# Exoplanet Summary API
  
## Development Environment Setup  
### Requirements  
* Java 12
  
### Steps  
#### 1. Clone GIT Projects for POC  
```bash  
git clone git@github.com:chagen19/exoplanet-summary-api.git
```  
#### 2. Start service  
From project root run:
```bash
./gradlew clean bootRun

```  

#### 2. Run Tests  
From project root run:
```bash
./gradlew clean test
```
 
#### 3. Test the app 
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