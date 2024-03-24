plugins {
    `java-library`
    id("java-conventions")
    id("i18n-conventions")
    id("querydsl")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.springframework.data:spring-data-envers")

    api("io.swagger.core.v3:swagger-annotations-jakarta")
    api("org.dromara.hutool:hutool-core")

    api(project(":api"))


    //compileOnly("io.github.perplexhub:rsql-jpa-spring-boot-starter")

    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-hibernate6")



}
