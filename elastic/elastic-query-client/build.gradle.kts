plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation(project(":app-config-data"))
    implementation(project(":common-util"))
    implementation(project(":elastic:elastic-config"))
    implementation(project(":elastic:elastic-model"))

    implementation("org.springframework.data:spring-data-elasticsearch:5.1.2")
}
