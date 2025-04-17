import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "3.1.2"
}

dependencies {
	implementation(project(":elastic:elastic-model"))

	implementation("org.springframework:spring-context")
	implementation("org.springframework:spring-web")
	implementation("org.springframework.security:spring-security-core")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.getByName<Jar>("jar") {
	enabled = true
}

tasks.getByName<BootJar>("bootJar") {
	enabled = false
}