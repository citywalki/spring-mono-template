plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    api("com.google.guava:guava")
    api("org.dromara.hutool:hutool-core")

    api("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    compileOnly(project(":squiggly"))
}