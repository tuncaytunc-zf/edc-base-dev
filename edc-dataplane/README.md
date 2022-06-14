# EDC Data-Plane

### Building

```shell
./gradlew clean build
```

### Configuration

Listed below are configuration keys needed to get the `edc-dataplane` up and running.
Details regarding each configuration property can be found at the [documentary section of the EDC](https://github.com/eclipse-dataspaceconnector/DataSpaceConnector/tree/main/docs).

| Key  	                                                | Required  | Example | Description |
|---	                                                |---        |---	  |---          |
| web.http.default.port                                 | X         | 8080    | |
| web.http.default.path                                 | X         | /api    | |
| web.http.public.port                                  | X         | 8181    | |
| web.http.public.path                                  | X         |         | |
| web.http.control.port                                 | X         | 9999 | |
| web.http.control.path                                 | X         | /api/controlplane/control | |
| edc.receiver.http.endpoint                            | X         | http://backend-service | |
| edc.hostname                                          |           | localhost | |
| edc.oauth.client.id                                   | X         | daps-oauth-client-id | |
| edc.vault.clientid                                    | X         | 00000000-1111-2222-3333-444444444444 | | 
| edc.vault.tenantid                                    | X         | 55555555-6666-7777-8888-999999999999 | |
| edc.vault.name                                        | X         | my-vault-name | |
| edc.vault.clientsecret                                | X         | 34-chars-secret | |
| edc.controlplane.validation-endpoint                  | X         | http://controlplane:8182/validation | | 

#### Example configuration.properties

JDK properties-style configuration of the EDC Control-Plane is expected to be mounted to `/app/configuration.properties` within the container.

```shell
# Create configuration.properties
export CONFIGURATION_PROPERTIES_FILE=$(mktemp /tmp/configuration.properties.XXXXXX)
cat << 'EOF' > ${CONFIGURATION_PROPERTIES_FILE}

web.http.default.port=8080
web.http.default.path=/api
web.http.public.port=8185
web.http.public.path=/public
web.http.control.port=9999
web.http.control.path=/api/dataplane/control

# Validation endpoint of controlplane
edc.controlplane.validation-endpoint=http://controlplane:8182/validation

# EDC hostname
edc.hostname=localhost

EOF
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
```shell
otel.javaagent.enabled=true
otel.javaagent.debug=false
```
