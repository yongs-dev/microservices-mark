plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation("org.twitter4j:twitter4j-stream:4.0.7")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.json:json:20231013")
}