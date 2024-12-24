import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "1.8.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("androidx.compose.ui:ui-text-google-fonts:1.8.1")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation("ship.f:engine")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

            implementation("ship.f.engine:client")
            implementation("ship.f.engine.client:core")
            implementation("ship.f.engine.client:ksp")
            implementation("ship.f.engine.client:utils")
            implementation("ship.f.engine.client.utils:accessibility")
            implementation("ship.f.engine.client.utils:lexeme")
            implementation("ship.f.engine.client.utils:monitoring")
            implementation("ship.f.engine.client.utils.monitoring:analytics")
            implementation("ship.f.engine.client.utils.monitoring:crash")
            implementation("ship.f.engine.client.utils.monitoring:error")
            implementation("ship.f.engine.client.utils.monitoring:performance")
            implementation("ship.f.engine.client.utils:networking")
            implementation("ship.f.engine.client.utils:pushnotifications")
            implementation("ship.f.engine.client.utils:serverdrivenui")

            implementation("ship.f.engine:kotlingen")
            implementation("ship.f.engine.kotlingen:dsl")
            implementation("ship.f.engine.kotlingen:gen")
            implementation("ship.f.engine.kotlingen:ksp")
            implementation("ship.f.engine.kotlingen.ksp:writer")
            implementation("ship.f.engine.kotlingen:taskmanager")
            implementation("ship.f.engine.kotlingen:writer")

            implementation("ship.f.engine:sample")
            implementation("ship.f.engine.sample:client")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "ship.f.project.x.client"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ship.f.project.x.client"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 7
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/*"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3.android)

    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "ship.f.project.x.client.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ship.f.project.x.client"
            packageVersion = "1.0.0"
        }
    }
}
