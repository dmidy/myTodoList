plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id ("war")
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
//	compileOnly("org.projectlombok:lombok:1.18.30")
	implementation("org.springframework:spring-webmvc:6.1.6")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.5")
	implementation("org.webjars:bootstrap:5.3.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}



tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<War>("bootWar") {
	archiveFileName.set("myTodoList.war")
	destinationDirectory.set(file("$buildDir/libs"))
}