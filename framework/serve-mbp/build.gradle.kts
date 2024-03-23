plugins {
    `java-library`
    id("java-conventions")
    id("message-conventions")
}


dependencies {
    api(project(":serve"))

    implementation("org.projectlombok:lombok")
    api(libs.mybatis.plus.spring.boot3.starter)
}
