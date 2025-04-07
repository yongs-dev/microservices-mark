plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":kafka:kafka-model"))

    implementation("org.springframework.kafka:spring-kafka:3.0.9")
    implementation("io.confluent:kafka-avro-serializer:7.4.0")
}