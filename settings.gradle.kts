import org.gradle.internal.extensions.stdlib.toDefaultLowerCase

rootProject.name = "ProjectX"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

fun renameBuildFileToModuleName(project: ProjectDescriptor, modules: List<ProjectDescriptor> = listOf()) {
    val updatedModules = modules + listOf(project)
    val projectName = "${updatedModules.joinToString("") { "${it.name.toDefaultLowerCase()}." }}gradle.kts"
    project.buildFileName = projectName
    project.children.forEach { renameBuildFileToModuleName(it, updatedModules) }
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

include(":composeApp")
include(":server")
include(":shared")

include(":Engine")
include(":Engine:Client")
include(":Engine:Client:Core")
include(":Engine:Client:KSP")
include(":Engine:Client:Utils")
include(":Engine:Client:Utils:Accessibility")
include(":Engine:Client:Utils:Lexeme")
include(":Engine:Client:Utils:Networking")
include(":Engine:Client:Utils:PushNotifications")
include(":Engine:Client:Utils:ServerDrivenUI")
include(":Engine:Client:Utils:Monitoring")
include(":Engine:Client:Utils:Monitoring:Analytics")
include(":Engine:Client:Utils:Monitoring:Crash")
include(":Engine:Client:Utils:Monitoring:Performance")
include(":Engine:Client:Utils:Monitoring:Error")
include(":Engine:Server")
include(":Engine:Server:Core")
include(":Engine:Server:KSP")
include(":Engine:Server:Utils")
include(":Engine:Server:Utils:Accessibility")
include(":Engine:Server:Utils:Lexeme")
include(":Engine:Server:Utils:Networking")
include(":Engine:Server:Utils:PushNotifications")
include(":Engine:Server:Utils:ServerDrivenUI")
include(":Engine:Server:Utils:Monitoring")
include(":Engine:Server:Utils:Monitoring:Analytics")
include(":Engine:Server:Utils:Monitoring:Crash")
include(":Engine:Server:Utils:Monitoring:Performance")
include(":Engine:Server:Utils:Monitoring:Error")
include(":Engine:KotlinGen")
include(":Engine:KotlinGen:DSL")
include(":Engine:KotlinGen:Gen")
include(":Engine:KotlinGen:KSP")
include(":Engine:KotlinGen:KSP:Writer")
include(":Engine:KotlinGen:TaskManager")
include(":Engine:KotlinGen:Writer")
include(":Engine:Sample")
include(":Engine:Sample:Client")
include(":Engine:Sample:Server")

rootProject.children.forEach {
    renameBuildFileToModuleName(it)
}