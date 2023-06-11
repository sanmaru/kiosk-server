plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val grpcVersion = "1.55.1"

    /* Springboot with gRPC */
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.github.lognet:grpc-spring-boot-starter:5.1.2")

    /* DataSource & Annotation & JDBC Connection*/
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok:1.18.26")

    /* postgresql DB */
    implementation("org.postgresql:postgresql:42.6.0")
    testImplementation("junit:junit:4.13.1")
    runtimeOnly("org.postgresql:postgresql")

    /* gRPC Proto file project */
    implementation(project(":grpcInterface"))

    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
    }

    classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it) {
                    exclude(
                            "**/monki/GRPCS**",
                            "**/monki/GRPCC**",
                            "**/monki/data/**"
                    )
                }
            })
    )

}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "PACKAGE"

            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }

            //Instructions
            limit {
                counter = "INSTRUCTION"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
            excludes = listOf(
                    "net.monki",
                    "net.monki.data.**"
            )
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform() // Note: automatically generated when creating project
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
}