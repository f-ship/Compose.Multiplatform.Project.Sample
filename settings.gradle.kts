rootProject.name = "ProjectX"
rootProject.buildFileName = "build.gradle.kts"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

fun renameBuildFileToModuleName(project: ProjectDescriptor, modules: List<ProjectDescriptor> = listOf()) {
    val updatedModules = modules + listOf(project)
    val projectName = "${updatedModules.joinToString("") { "${it.name.lowercase()}." }}gradle.kts"
    project.buildFileName = projectName
}

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

includeBuild("Engine")
include(":composeApp")
include(":shared")
include(":server")

rootProject.children.forEach {
    renameBuildFileToModuleName(it)
}