plugins {
    `java-library`
    id("java-conventions")
}



dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    api("cn.dev33:sa-token-spring-boot3-starter")
    api("cn.dev33:sa-token-jwt")

    api("org.springframework.boot:spring-boot-starter-thymeleaf")

    api(project(":domain"))
    api(project(":service"))
    api(project(":api"))

    api("pro.walkin.framework:spring-boot-starter")

}
