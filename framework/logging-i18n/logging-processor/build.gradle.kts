plugins {
    `java-library`
    id("java-conventions")
}

dependencies {
    implementation(project(":logging-i18n:logging-annotations"))

    implementation("org.jboss.jdeparser:jdeparser:2.0.2.Final")
}