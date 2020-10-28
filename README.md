# Airlines App

### Introduction
This app demonstrates how the OpenAPI feature can be used along with JAXRS annotations to document your REST APIs in a neat and organized manner. You can specify the following with the OpenAPI feature: 

  - Available endpoints and operations on each endpoint 
  - Operation parameters Input and output for each operation
  - Authentication methods
  - Contact information, license, terms of use and other information.


### Getting Started
1. Clone this repository 
```
git clone https://github.com/microservices-api/oas3-airlines.git
```
2. Download the Liberty [jar](https://developer.ibm.com/wasdev/downloads/liberty-profile-beta/) and decompress
3. Create a new server called myServer by running the following commands
```
$ cd wlp/bin
$ ./server create myServer
```
4. Copy the file `oas3-airlines/deployment_artifacts/server.xml` into `wlp/usr/servers/myServer`
5. Copy the file `oas3-airlines/deployment_artifacts/airlines.war` into `wlp/usr/servers/myServer/apps`
6. Copy the directory `oas3-airlines/deployment_artifacts/custom` into `wlp/usr/servers/myServer`
7. Install the needed features by running
```
$ ./installUtility install --acceptLicense myServer
```
8. Start the server by running 
```
$ ./server start myServer
```
9. Open a browser and navigate to `http://<your_host>/api/explorer` to see the OpenAPI UI

