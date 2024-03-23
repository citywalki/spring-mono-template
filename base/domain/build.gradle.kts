plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    api("pro.walkin.framework:event-drive")
    api("pro.walkin.framework:serve-mbp")

    compileOnly("com.fasterxml.jackson.core:jackson-databind")

}
