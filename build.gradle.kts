
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.5"
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
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    // MyBatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")

    // Database
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")

    // Thymeleaf Extras
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

    // SpringDoc (Swagger UI)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Commons
    implementation("commons-io:commons-io:2.7")
    implementation("commons-fileupload:commons-fileupload:1.5")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Exclude Lombok from the final JAR
tasks.withType<BootJar> {
    layered {
        dependencies {
            exclude("org.projectlombok:lombok")
        }
    }
}
