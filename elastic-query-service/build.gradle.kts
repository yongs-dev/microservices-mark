import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":common-util"))
    implementation(project(":elastic:elastic-config"))
    implementation(project(":elastic:elastic-model"))
    implementation(project(":elastic:elastic-query-client"))
    implementation(project(":elastic-query-service-common"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/elastic.query.service:${project.version}")
    builder.set("paketobuildpacks/builder-jammy-base:latest")
    environment.set(mapOf("BP_JVM_VERSION" to "21.*"))
}