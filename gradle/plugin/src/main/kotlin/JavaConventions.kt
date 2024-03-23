import io.freefair.gradle.plugins.lombok.LombokPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.api.tasks.testing.Test
import org.gradle.external.javadoc.StandardJavadocDocletOptions
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.withType

class JavaConventions : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(LombokPlugin::class)

        target.plugins.withType(JavaPlugin::class) {
            target.setProperty("sourceCompatibility", JavaVersion.VERSION_21)

            target.dependencies {
                add(
                    JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME,
                    enforcedPlatform("pro.walkin.framework:dependencies")
                )
                add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, enforcedPlatform("pro.walkin.framework:dependencies"))

                add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.jboss.logging:jboss-logging")

                //compileOnly 'org.projectlombok:lombok:1.18.32'
                //annotationProcessor 'org.projectlombok:lombok:1.18.32'

                add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.projectlombok:lombok")
                //add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "org.projectlombok:lombok")

            }

            target.configurations.all {
                exclude("org.springframework.boot", "spring-boot-starter-logging")
                exclude("ch.qos.logback", "logback-classic")
                exclude("ch.qos.logback", "logback-core")
                exclude("org.apache.poi", "poi-ooxml-schemas")
                exclude("com.google.code.findbugs")

                //resolutionStrategy {
                //    force("org.apache.logging.log4j:log4j-api:2.17.2")
                //}
            }
        }

        target.tasks.withType<Test> {
            useJUnitPlatform()
            systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
            maxHeapSize = "1024M"
        }

        target.tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.compilerArgs.add("-parameters")
            sourceCompatibility = JavaVersion.VERSION_21.majorVersion
            targetCompatibility = JavaVersion.VERSION_21.majorVersion
        }

        target.tasks.withType<Javadoc> {
            options {
                encoding("UTF-8").source(JavaVersion.VERSION_21.majorVersion)
                (this as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
            }
        }
    }
}
