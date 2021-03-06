import dependencies.*
import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}


val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = localProperties["signing.key.alias"].toString()
            keyPassword = localProperties["signing.key.password"].toString()
            storeFile = file(localProperties["signing.store.file"]!!)
            storePassword = localProperties["signing.store.password"].toString()
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.debuggable
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(name)
        }

        debug {
            applicationIdSuffix = BuildTypeDebug.APPLICATION_ID_SUFFIX
            versionNameSuffix = BuildTypeDebug.VERSION_SUFFIX
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isShrinkResources = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.debuggable
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

}

android.sourceSets.all {
    kotlin.srcDir("src/$name/kotlin")
}

dependencies {
    implementation(JetpackDependencies.CORE_KTX)
    implementation(UiDependencies.ACTIVITY)
    implementation(UiDependencies.APPCOMPAT)
    implementation(UiDependencies.MATERIAL)
    implementation(UiDependencies.CONSTRAINT_LAYOUT)
    implementation(UiDependencies.DRAWER_LAYOUT)
    implementation(UiDependencies.RECYCLER_VIEW)
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.EXT_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
    implementation(GeneralDependencies.RX_JAVA)
    implementation(GeneralDependencies.RX_ANDROID)
    implementation(GeneralDependencies.RX_KOTLIN)
    kapt(AnnotationProcessorsDependencies.ROOM)
    implementation(JetpackDependencies.ROOM)
    implementation(JetpackDependencies.ROOM_KTX)
    implementation(JetpackDependencies.ROOM_RX)
    implementation(GeneralDependencies.JAVAX_INJECT)
    kapt(AnnotationProcessorsDependencies.DAGGER)
    implementation(GeneralDependencies.DAGGER)
    implementation(JetpackDependencies.NAVIGATION_FRAGMENT)
    implementation(JetpackDependencies.NAVIGATION_UI)
    implementation(JetpackDependencies.FRAGMENT)
    implementation(UiDependencies.GLIDE)
    kapt(AnnotationProcessorsDependencies.GLIDE_COMPILER)
    implementation(JetpackDependencies.LIFECYCLE_VIEWMODEL)
    implementation(JetpackDependencies.LIFECYCLE_LIVEDATA)
    implementation(JetpackDependencies.LIFECYCLE_SAVE_STATE)
    kapt(AnnotationProcessorsDependencies.LIFECYCLE)
}