import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":kafka:kafka-admin"))
    implementation(project(":kafka:kafka-model"))
    implementation(project(":kafka:kafka-producer"))

    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("org.twitter4j:twitter4j-stream:4.0.7")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.json:json:20231013")
    implementation("org.apache.avro:avro:1.12.0")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/twitter.to.kafka.service:${project.version}")
    builder.set("paketobuildpacks/builder-jammy-base:latest")
    environment.set(mapOf("BP_JVM_VERSION" to "21.*"))
}