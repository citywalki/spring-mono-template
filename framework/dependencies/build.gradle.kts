plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

tasks.withType<GenerateModuleMetadata> {
    // The value 'enforced-platform' is provided in the validation
    suppressedValidationErrors.add("enforced-platform")
}

dependencies {
    api(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:${libs.versions.spring.boot.get()}"))
    api(platform("org.apache.camel.springboot:camel-spring-boot-bom:${libs.versions.camel.get()}"))
    api(platform("org.dromara.hutool:hutool-bom:${libs.versions.hutool.get()}"))

    constraints {
        api("org.jboss.logging:jboss-logging:3.4.2.Final")
        api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
        api("io.swagger.core.v3:swagger-annotations-jakarta:2.2.20")
        api("com.google.guava:guava:33.0.0-jre")
        api("cn.dev33:sa-token-spring-boot3-starter:1.37.0")
        api("cn.dev33:sa-token-jwt:1.37.0")
        api("io.github.perplexhub:rsql-jpa-spring-boot-starter:6.0.18")

        api("com.alibaba:transmittable-thread-local:2.14.4")
        api("com.alibaba:log4j2-ttl-thread-context-map:1.4.0")

        api("com.github.jsqlparser:jsqlparser:4.9")
        api("cz.jirutka.rsql:rsql-parser:2.1.0")

        api("com.querydsl:querydsl-apt:5.0.0:jpa")
        api("com.querydsl:querydsl-jpa:5.0.+")

        api("javax.persistence:persistence-api:1.0.2")
    }
}