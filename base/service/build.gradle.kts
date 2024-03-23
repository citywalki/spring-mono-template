plugins {
    `java-library`
    id("java-conventions")
    id("message-conventions")
}

dependencies {
    implementation(project(":domain"))

    implementation("org.dromara.hutool:hutool-crypto")

    compileOnly("cn.dev33:sa-token-spring-boot3-starter")

}
