import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "3.1.2"
}

dependencies {
	implementation(project(":app-config-data"))
	implementation(project(":elastic:elastic-config"))
	implementation(project(":elastic:elastic-index-client"))
	implementation(project(":elastic:elastic-model"))
	implementation(project(":kafka:kafka-admin"))
	implementation(project(":kafka:kafka-consumer"))
	implementation(project(":kafka:kafka-model"))

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.kafka:spring-kafka:3.0.9")
	implementation("io.confluent:kafka-avro-serializer:7.4.0")
}

tasks.named<BootBuildImage>("bootBuildImage") {
	imageName.set("${project.group}/kafka.to.elastic.service:${project.version}")
	builder.set("paketobuildpacks/builder-jammy-base:latest")
	environment.set(mapOf("BP_JVM_VERSION" to "21.*"))
}