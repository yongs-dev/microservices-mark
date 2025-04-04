plugins {
    id("org.springframework.boot") version "3.1.2"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

dependencies {
    implementation("org.apache.avro:avro:1.12.0")
}

avro {
    stringType.set("String")
}
