pluginManagement {
    includeBuild("../gradle/plugin")

    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }

}

rootProject.name = "bootstrap"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../framework")
includeBuild("../base")
