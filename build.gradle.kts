plugins {
    val protobufPlugVersion = "0.9.2"

    id("java")
    id("com.google.protobuf") version "$protobufPlugVersion" apply false
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects{
    group = "org.monki"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

}

subprojects {

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
