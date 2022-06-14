# Easy to build and run EDC components

This project provides an easy to build and run of Control-Plane and Data-Plane with a simple backend data service.

## Inventory

The eclipse data space connector is split up into Control-Plane and Data-Plane, whereas the Control-Plane functions as administration layer
and has responsibility of resource management, contract negotiation and administer data transfer. 
The Data-Plane does the heavy lifting of transferring and receiving data streams.
The backend data service is just for creating and retrieving some dummy string values via API-Calls.

Controlplane

* [edc-controlplane-memory](edc-controlplane-memory)

Data-Plane

* [edc-dataplane](edc-dataplane)

Backend data service

* [backend-data-service](backend-data-service)

Self signed certificates for only development purpose can be found here

* [certs](certs)

## Prerequisites

#### EDC artifacts

Since the [EDC](https://github.com/eclipse-dataspaceconnector/DataSpaceConnector) does 
not yet publish artifacts to a maven repository, which this project relies on, it needs 
to be built upfront to be used:

```shell
git submodule update --init
cd edc && ./gradlew publishToMavenLocal -x test
```


## Build

### Build EDC-Controlplane-Memory
```shell
cd edc-controlplane-memory && ./gradlew clean build
```

### Build EDC-Dataplane
```shell
cd edc-dataplane && ./gradlew clean build
```

### Build Backend-Data-Service
```shell
cd backend-data-service && ./gradlew clean build
```

## Run
This project includes multiple setup/run possibilities.

####  Run self-build multiple docker container with: 
* edc-controlplane-memory (provider)
* edc-dataplane (provider)
* edc-controlplane-memory (consumer)
* edc-dataplane (consumer)
* backend-data-service

```shell
docker-compose up --build
```

####  Run self-build multiple docker container with:
* edc-controlplane-memory (provider)
* edc-dataplane (provider)
* edc-controlplane-memory (consumer)
* edc-dataplane (consumer)
* backend-data-service
* api-wrapper

```shell
docker-compose -f docker-compose-with-api-wrapper.yml up --build
```
## Test
With the Postman Collection "edc-base-dev_API-Calls.postmann_collection.json" you can test the whole setup.

## Known Issues
The following SF4J Error can be ignored:

java.lang.AbstractMethodError: Receiver class org.eclipse.dataspaceconnector.boot.monitor.MonitorProvider does not define or inherit an implementation of the resolved method 'abstract java.lang.String getRequestedApiVersion()' of interface org.slf4j.spi.SLF4JServiceProvider.