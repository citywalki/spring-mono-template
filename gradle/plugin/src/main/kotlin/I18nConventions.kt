import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class I18nConventions : Plugin<Project> {
    override fun apply(target: Project) {

        target.dependencies {
            //add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.jboss.logging:jboss-logging")
            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "pro.walkin.framework:logging-annotations")

            add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "pro.walkin.framework:logging-annotations")
            add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "pro.walkin.framework:logging-processor")
            //add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.jboss.logging:jboss-logging")
            //add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "io.iamcyw.tower.logging:jboss-logging-annotations")

            //add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "org.jboss.logging:jboss-logging")
            //add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "io.iamcyw.tower.logging:jboss-logging-annotations")
            //add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "io.iamcyw.tower.logging:tower-logging-processor")

        }

        target.tasks.withType<JavaCompile>().configureEach {
            options.compilerArgs = listOf("-AtranslationFilesPath=${project.parent?.projectDir?.path.plus("/i18n/")}")
        }
    }
}