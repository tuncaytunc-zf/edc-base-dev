plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val javaVersion = 11

dependencies {
    val edcGroup = "org.eclipse.dataspaceconnector"
    val edcVersion = "0.0.1-SNAPSHOT"

    api("$edcGroup:filesystem-configuration:$edcVersion")
    api("$edcGroup:filesystem-vault:$edcVersion")
    api("$edcGroup:data-plane-framework:$edcVersion")
    api("$edcGroup:data-plane-http:$edcVersion")
    api("$edcGroup:data-plane-api:$edcVersion")
    api("$edcGroup:observability-api:$edcVersion")
    api("$edcGroup:core-base:$edcVersion")
    api("$edcGroup:core-boot:$edcVersion")
    api("$edcGroup:core-micrometer:$edcVersion")
    api("$edcGroup:jersey-micrometer:$edcVersion")
    api("$edcGroup:jetty-micrometer:$edcVersion")
    api("$edcGroup:jdk-logger-monitor:$edcVersion")
    api("$edcGroup:http:$edcVersion")
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
    exclude("**/pom.properties", "**/pom.xm", "jndi.properties", "jetty-dir.css", "META-INF/maven/**")
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
