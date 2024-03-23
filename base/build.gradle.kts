subprojects {
    group = "pro.walkin.base"
    version = "1.0-SNAPSHOT"

    repositories {
        maven("https://mirrors.huaweicloud.com/repository/maven/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        mavenLocal()
        mavenCentral()
    }
}
