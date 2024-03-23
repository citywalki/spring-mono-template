plugins {
    `java-library`
    id("java-conventions")
    id("message-conventions")
}



dependencies {
    api(project(":api"))
    api(project(":core"))

    api("com.fasterxml.jackson.core:jackson-annotations")
    api("io.swagger.core.v3:swagger-annotations-jakarta")

    compileOnly("com.alibaba:transmittable-thread-local")
    compileOnly("cn.dev33:sa-token-spring-boot3-starter")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.springframework.data:spring-data-envers")
    compileOnly("io.github.perplexhub:rsql-jpa-spring-boot-starter")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate6")



}
