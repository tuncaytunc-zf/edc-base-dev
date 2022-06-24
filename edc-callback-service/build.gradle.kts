plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("java")
}

val javaVersion = 11

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
}

repositories {
	mavenLocal()
	mavenCentral()
}
