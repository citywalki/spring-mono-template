plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    api(project(":domain"))
    api(project(":service"))

    compileOnly("org.apache.camel.springboot:camel-spring-boot-starter")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-thymeleaf")
    compileOnly("org.springframework.boot:spring-boot-starter-graphql")
    compileOnly("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    compileOnly("cn.dev33:sa-token-spring-boot3-starter")

}
