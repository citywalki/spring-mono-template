import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.ide.idea.IdeaPlugin

class QueryDSLConventions : Plugin<Project> {
    override fun apply(target: Project) {

        target.dependencies {
            add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME,"jakarta.persistence:jakarta.persistence-api")
            add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "com.querydsl:querydsl-apt:5.1.0:jakarta")
            add(JavaPlugin.API_CONFIGURATION_NAME, "com.querydsl:querydsl-jpa:5.1.0:jakarta")
        }

        target.plugins.withType(IdeaPlugin::class) {
            this.model.module {
                sourceDirs =
                    sourceDirs.plus(target.file("${target.projectDir}/build/generated/sources/annotationProcessor/java/main/"))
            }
        }
    }

}