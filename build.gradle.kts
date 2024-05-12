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
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.5")
	implementation("org.springframework.boot:spring-boot-starter-security:3.2.5")
	implementation("org.springframework.security:spring-security-core:6.2.4")
	implementation("org.springframework.security:spring-security-config:6.2.4")
	implementation("org.springframework.security:spring-security-web:6.2.4")
	implementation("org.springframework.security:spring-security-crypto:6.2.4")
	implementation("com.h2database:h2:2.2.224")
	implementation("org.flywaydb:flyway-core:10.9.1")
	implementation("org.webjars:bootstrap:5.3.3")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}



tasks.withType<Test> {
	useJUnitPlatform()
}
