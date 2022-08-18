plugins {
	`java-library`
	id("application")
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

val edcGroup = "org.eclipse.dataspaceconnector"
val edcVersion = "0.0.1-SNAPSHOT"
val javaVersion = 11

dependencies {
	implementation("$edcGroup:core-boot:$edcVersion")
	implementation("$edcGroup:core-base:$edcVersion")
	implementation("$edcGroup:http:$edcVersion")
	implementation("$edcGroup:filesystem-configuration:$edcVersion")

	implementation("com.squareup.okhttp3:okhttp:4.9.3")
	implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
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
	archiveFileName.set("edc-backend-service.jar")
}

repositories {
	mavenLocal()
	mavenCentral()
}
