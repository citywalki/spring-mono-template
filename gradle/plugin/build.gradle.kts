plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.freefair.gradle:lombok-plugin:8.6")
}

gradlePlugin {
    plugins.register("JavaConventions") {
        id = "java-conventions"
        implementationClass = "JavaConventions"
    }
    plugins.register("MessageConventions") {
        id = "message-conventions"
        implementationClass = "MessageConventions"
    }
}
