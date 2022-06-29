plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val javaVersion = 11

dependencies {
    val edcGroup = "org.eclipse.dataspaceconnector"
    val edcVersion = "0.0.1-SNAPSHOT"

    // Config
    api("$edcGroup:filesystem-configuration:$edcVersion")

    // Vault
    api("$edcGroup:filesystem-vault:$edcVersion")

    // APIs
//    api("$edcGroup:control-api:$edcVersion")
    api("$edcGroup:data-management-api:$edcVersion")
    api("$edcGroup:observability-api:$edcVersion")

    // IDS
    api("$edcGroup:ids-api-configuration:$edcVersion")
    api("$edcGroup:ids-api-multipart-dispatcher-v1:$edcVersion")
    api("$edcGroup:ids-api-multipart-endpoint-v1:$edcVersion")
    api("$edcGroup:ids-api-transform-v1:$edcVersion")
    api("$edcGroup:ids-core:$edcVersion")
    api("$edcGroup:ids-spi:$edcVersion")
    api("$edcGroup:ids-token-validation:$edcVersion")

    // Core
    api("$edcGroup:core-base:$edcVersion")
    api("$edcGroup:core-boot:$edcVersion")
    api("$edcGroup:transfer:$edcVersion")
    api("$edcGroup:contract:$edcVersion")

    // Data-Plane
    api("$edcGroup:data-plane-transfer-sync:$edcVersion")

    // IAM
    api("$edcGroup:iam-mock:$edcVersion")

    // Telemetry
    api("$edcGroup:core-micrometer:$edcVersion")
    api("$edcGroup:jersey-micrometer:$edcVersion")
    api("$edcGroup:jetty-micrometer:$edcVersion")

    // Logging
    api("$edcGroup:jdk-logger-monitor:$edcVersion")

    // Supportive includes jetty and jersey
    api("$edcGroup:http:$edcVersion")
    api("$edcGroup:http-receiver:$edcVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

application {
    mainClass.set("org.eclipse.dataspaceconnector.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xm")
    mergeServiceFiles()
    archiveFileName.set("edc.jar")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.iais.fraunhofer.de/artifactory/eis-ids-public/")
    }
}
