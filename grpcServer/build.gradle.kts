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

    /* Code Corverage */
//    implementation("org.jacoco:jacoco-maven-plugin:0.8.10")

    /* postgresql DB */
    implementation("org.postgresql:postgresql:42.6.0")
    testImplementation("junit:junit:4.13.1")
    runtimeOnly("org.postgresql:postgresql")

    /* gRPC Proto file project */
    implementation(project(":grpcInterface"))
//    implementation("io.github.lognet:grpc-spring-boot-starter:5.1.2"){
//        exclude("io.grpc", "grpc-netty-shaded")
//    }
//    implementation("io.grpc:grpc-protobuf:$grpcVersion")
//    implementation("io.grpc:grpc-stub:$grpcVersion")
//    implementation("io.grpc:grpc-services:$grpcVersion")

//    compileOnly("io.grpc:grpc-netty:1.55.1")
//    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.testcontainers:junit-jupiter:1.15.3")
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
                    exclude("**/monki/GRPC**", "**/monki/data/**")
                }
            })
    )

}



tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "CLASS"

            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.10".toBigDecimal()
            }
            excludes = listOf(
                    "net.monki.GRPC**",
                    "net.monki.data.**"
            )
        }
    }
}

tasks.withType<Test> {
//    exclude("net.monki.GRPCServer**")
//    extensions.configure(JacocoTaskExtension::class) {
//        isEnabled = true
//        excludes = listOf("**/GRPCServer**", "**/GRPCConfig**")
//    }
    useJUnitPlatform() // Note: automatically generated when creating project
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
}