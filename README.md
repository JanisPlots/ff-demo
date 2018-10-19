
## Live demo

[http://192.168.99.102/swagger-ui.html](http://192.168.99.102/swagger-ui.html)


## Run application

Pre-requisites

* Java 8 JDK

### Run application locally

Windows

```shell
mvnw.cmd spring-boot:run
```

Unix

```shell
mvnw spring-boot:run
```

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Launch application with docker compose

Pre-requisites Windows
* [Docker toolbox](https://docs.docker.com/toolbox/)


Pre-requisites Unix
* [Docker](https://docs.docker.com/install/)

```shell
cd docker-compose/
./launch.sh
```