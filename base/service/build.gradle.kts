plugins {
    `java-library`
    id("java-conventions")
    id("i18n-conventions")
}

dependencies {
    implementation(project(":domain"))
    implementation("pro.walkin.framework:serve")

    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("cn.dev33:sa-token-spring-boot3-starter")

    compileOnly("cn.dev33:sa-token-spring-boot3-starter")

}
