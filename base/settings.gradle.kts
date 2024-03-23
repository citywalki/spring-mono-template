pluginManagement {
    includeBuild("../gradle/plugin")

    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }

}

rootProject.name = "base"

//include("view")
include("domain")
include("service")
include("api")
include("spring-boot-starter")


dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
