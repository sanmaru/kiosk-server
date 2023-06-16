plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "net.monki"
version = "1.0-SNAPSHOT"
val grpcVersion = "1.55.1"
val lognetStarterVersion = "5.1.2"

repositories {
    mavenCentral()
}

dependencies {
    /* Springboot with gRPC */

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.github.lognet:grpc-spring-boot-starter:$lognetStarterVersion")

    implementation("org.projectlombok:lombok")

    /* gRPC Proto file project */
    implementation(project(":grpcInterface"))

    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}