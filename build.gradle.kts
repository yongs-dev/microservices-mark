plugins {
    java
    id("org.springframework.boot") version "3.1.2" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

allprojects {
    group = "com.microservices.mark"
    version = "0.0.1-SNAPSHOT"

    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    group = "com.microservices.mark"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }

    dependencies {
        configurations.all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")

        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")

        testCompileOnly("org.projectlombok:lombok:1.18.30")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.register("prepareKotlinBuildScriptModel") {}
}