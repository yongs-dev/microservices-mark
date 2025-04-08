plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))

    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.elasticsearch:elasticsearch:7.17.4")
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.17.4")
}
