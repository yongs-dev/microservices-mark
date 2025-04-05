import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.1.2"
}

dependencies {
    implementation("com.github.ulisesbocchio:jasypt-spring-boot:3.0.5")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = false
}