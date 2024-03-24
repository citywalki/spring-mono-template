plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-log4j2")
    api("org.springframework.boot:spring-boot-starter-validation")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.data:spring-data-envers")

    implementation("com.alibaba:transmittable-thread-local")
    implementation("com.alibaba:log4j2-ttl-thread-context-map")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate6")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    api(project(":squiggly"))
    api(project(":serve"))
    api(project(":event-drive"))

}
