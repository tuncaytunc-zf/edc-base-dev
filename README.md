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

API-Wrapper (pre-build docker image from catenax-at-home)

* [api-wrapper](https://github.com/catenax-ng/catenax-at-home)

Omejdn (pre-build docker image for DAPS from Fraunhofer)

* [omejdn](https://github.com/Fraunhofer-AISEC/omejdn-server)


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

### Build EDC-Controlplane-Memory with DAPS (Omejdn)
```shell
cd edc-controlplane-memory-daps && ./gradlew clean build
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
This project contains multiple modules as docker-compose files which can be run separately or in combination within the same docker-network.
* dc-provider-bds.yml: Provider EDC with a dummy backend-data-service.
* dc-provider-daps.yml: Provider EDC with DAPS (Omejdn) integration.
* dc-consumer.yml: Consumer EDC.
* dc-consumer-daps.yml: Consumer EDC with DAPS (Omejdn) integration. 
* dc-daps.yml: Pre-build DAPS (Omejdn) server.
* dc-api-wrapper.yml: Pre-build API-Wrapper.

To run each of them separately or in combination please use the following command:

Run single component:
```
docker-compose -f <docker-compose-file-name> up --build
```
Run multiple components in same docker network:
```
docker-compose -f <docker-compose-file-name> ... -f <docker-compose-file-name> up --build
For example: "docker-compose -f dc-provider-bds-daps.yml -f dc-consumer-daps.yml -f dc-daps.yml -f dc-api-wrapper.yml up --build" 
```

## Test
With the Postman Collection "edc-base-dev_API-Calls.postmann_collection.json" you can test the whole setup.

## Known Issues
The following SF4J Error can be ignored:

java.lang.AbstractMethodError: Receiver class org.eclipse.dataspaceconnector.boot.monitor.MonitorProvider does not define or inherit an implementation of the resolved method 'abstract java.lang.String getRequestedApiVersion()' of interface org.slf4j.spi.SLF4JServiceProvider.