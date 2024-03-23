plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    api("com.google.guava:guava")
    api("org.dromara.hutool:hutool-core")

    api("jakarta.validation:jakarta.validation-api")
    api("jakarta.annotation:jakarta.annotation-api")

    implementation("jakarta.persistence:jakarta.persistence-api")

    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-context")

}
