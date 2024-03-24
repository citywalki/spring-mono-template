pluginManagement {
    includeBuild("../gradle/plugin")

    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }

}

rootProject.name = "framework"

include("squiggly")
include("api")
include("core")
include("event-drive")
include("serve")
//include("serve-mbp")
include("model")
include("spring-boot-starter")
include("dependencies")

include("logging-i18n:logging-annotations")
include("logging-i18n:logging-processor")

//include("rsql:mybatis")
//project(":spring-boot-starter").name = "walkin-framework-spring-boot-starter"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
