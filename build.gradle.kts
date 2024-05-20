plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
	implementation("com.h2database:h2:2.2.220")
	implementation("org.flywaydb:flyway-core:10.13.0")
	implementation("jakarta.validation:jakarta.validation-api:3.1.0-M2")
	implementation("org.apache.commons:commons-lang3:3.14.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}



tasks.withType<Test> {
	useJUnitPlatform()
}
