plugins {
    `java-library`
    id("java-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.graalvm.buildtools)
}

group = "pro.walkin.boot"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://mirrors.huaweicloud.com/repository/maven/")
    //maven("https://maven.aliyun.com/repository/central")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    mavenLocal()
    mavenCentral()
}


dependencies {
    implementation("org.postgresql:postgresql:${libs.versions.postgresql}")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    api("pro.walkin.base:spring-boot-starter")


}