plugins {
	id("org.springframework.boot") version "3.1.2"
}

dependencies {
	implementation(project(":app-config-data"))
	implementation(project(":elastic:elastic-model"))

	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
}
