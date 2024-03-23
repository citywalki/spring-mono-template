plugins {
    antlr
    `java-library`
    id("java-conventions")
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")

    implementation("org.apache.commons:commons-lang3")
    implementation("com.google.guava:guava")

    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("commons-beanutils:commons-beanutils:1.9.4")
    implementation("net.jcip:jcip-annotations:1.0")
    implementation("jakarta.servlet:jakarta.servlet-api")
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor", "-long-messages")
}