import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":elastic-query-web-client-common"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/reactive.elastic.query.web.client:${project.version}")
    builder.set("paketobuildpacks/builder-jammy-base:latest")
    environment.set(mapOf("BP_JVM_VERSION" to "21.*"))
}