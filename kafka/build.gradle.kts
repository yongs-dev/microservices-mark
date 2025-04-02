import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "3.1.2"
}

dependencies {

}

tasks.getByName<Jar>("jar") {
	enabled = false
}

tasks.getByName<BootJar>("bootJar") {
	enabled = false
}