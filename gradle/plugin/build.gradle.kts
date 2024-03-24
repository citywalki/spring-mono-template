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
    plugins.register("I18nConventions") {
        id = "i18n-conventions"
        implementationClass = "I18nConventions"
    }
    plugins.register("QueryDSLConventions") {
        id = "querydsl"
        implementationClass = "QueryDSLConventions"
    }
}
