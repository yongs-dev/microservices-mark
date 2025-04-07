import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-aop")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/config.server:${project.version}")
    builder.set("paketobuildpacks/builder-jammy-base:latest")
    environment.set(mapOf("BP_JVM_VERSION" to "21.*"))
}