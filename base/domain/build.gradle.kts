plugins {
    `java-library`
    id("java-conventions")
    //id("querydsl")
}

dependencies {
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("com.querydsl:querydsl-apt:5.1.0:jakarta")

    api("com.querydsl:querydsl-jpa:5.1.0:jakarta")

    api("pro.walkin.framework:event-drive")
    api("pro.walkin.framework:model")

    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    compileOnly("org.springframework.data:spring-data-envers")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
}
