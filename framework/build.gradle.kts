subprojects {
    group = "pro.walkin.framework"
    version = "1.0-SNAPSHOT"

    repositories {
        maven("https://mirrors.huaweicloud.com/repository/maven/")
        //maven("https://maven.aliyun.com/repository/central")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        mavenLocal()
        mavenCentral()

    }
}
