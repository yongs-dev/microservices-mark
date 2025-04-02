plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":common-config"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka:3.0.9")
}