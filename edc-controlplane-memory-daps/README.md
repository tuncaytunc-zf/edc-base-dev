# EDC Control-Plane backed by In-Memory Stores

### Building

```shell
./gradlew clean build
```

### Configuration (configuration.properties)

Listed below are configuration keys needed to get the `edc-controlplane-memory` up and running.
Details regarding each configuration property can be found at the [documentary section of the EDC](https://github.com/eclipse-dataspaceconnector/DataSpaceConnector/tree/main/docs).

| Key  	                                                | Required  | Example | Description |
|---	                                                |---	    |---	  |---          |
| web.http.default.port                                 | X         | 8080    | |
| web.http.default.path                                 | X         | /api    | |
| web.http.data.port                                    | X         | 8181    | |
| web.http.data.path                                    | X         | /data | |
| web.http.validation.port                              | X         | 8182    | |
| web.http.validation.path                              | X         | /validation | |
| web.http.control.port                                 | X         | 9999 | |
| web.http.control.path                                 | X         | /api/controlplane/control | |
| web.http.ids.port                                     | X         | 8282 | |
| web.http.ids.path                                     | X         | /api/v1/ids | |
| edc.receiver.http.endpoint                            | X         | http://backend-service | |
| edc.ids.title                                         |           | Eclipse Dataspace Connector | |
| edc.ids.description                                   |           | Eclipse Dataspace Connector | |
| edc.ids.id                                            |           | urn:connector:edc | |
| edc.ids.security.profile                              |           | base | |
| edc.ids.endpoint                                      |           | http://localhost:8282/api/v1/ids | |
| edc.ids.maintainer                                    |           | http://localhost | |
| edc.ids.curator                                       |           | http://localhost | |
| edc.ids.catalog.id                                    |           | urn:catalog:default | |
| ids.webhook.address                                   |           | http://localhost:8282/api/v1/ids | |
| edc.api.control.auth.apikey.key                       |           | X-Api-Key | |
| edc.api.control.auth.apikey.value                     |           | super-strong-api-key | |
| edc.hostname                                          |           | localhost | |
| edc.transfer.proxy.endpoint                  | X         | | |
| edc.transfer.proxy.token.signer.privatekey.alias  | X         | | |

#### Example configuration.properties

JDK properties-style configuration of the EDC Control-Plane is expected to be mounted to `/app/configuration.properties` within the container.

# Create configuration.properties
```
web.http.default.port=8080
web.http.default.path=/api
web.http.data.port=8181
web.http.data.path=/data
web.http.validation.port=8182
web.http.validation.path=/validation
web.http.control.port=9999
web.http.control.path=/api/controlplane/control
web.http.ids.port=8282
web.http.ids.path=/api/v1/ids

edc.receiver.http.endpoint=http://backend-service

edc.ids.title=Eclipse Dataspace Connector
edc.ids.description=Eclipse Dataspace Connector
edc.ids.id=urn:connector:edc
edc.ids.security.profile=base
edc.ids.endpoint=http://localhost:8282/api/v1/ids
edc.ids.maintainer=http://localhost
edc.ids.curator=http://localhost
edc.ids.catalog.id=urn:catalog:default
ids.webhook.address=http://localhost:8282/api/v1/ids

edc.api.control.auth.apikey.key=X-Api-Key
edc.api.control.auth.apikey.value=pass

edc.hostname=localhost
```

# Control- / Data- Plane configuration
```
edc.transfer.proxy.endpoint=http://dataplane-public-endpoint/public
```

#### Example logging.properties
```
.level=INFO
org.eclipse.dataspaceconnector.level=ALL
handlers=java.util.logging.ConsoleHandler
java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
java.util.logging.ConsoleHandler.level=ALL
java.util.logging.SimpleFormatter.format=[%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS] [%4$-7s] %5$s%6$s%n
```

#### Example opentelemetry.properties
```
otel.javaagent.enabled=true
otel.javaagent.debug=false
```