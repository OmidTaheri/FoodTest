import dependencies.JetpackDependencies
import dependencies.UiDependencies
import dependencies.TestDependencies

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id (BuildPlugins.KOTLIN_ANDROID)
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner =  BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE){
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.debuggable
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName(BuildTypes.DEBUG){
            applicationIdSuffix = BuildTypeDebug.APPLICATION_ID_SUFFIX
            versionNameSuffix = BuildTypeDebug.VERSION_SUFFIX
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isShrinkResources = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.debuggable
        }

    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

android.sourceSets.all {
    kotlin.srcDir("src/$name/kotlin")
}

dependencies {
    implementation (JetpackDependencies.CORE_KTX)
    implementation (UiDependencies.APPCOMPAT)
    implementation (UiDependencies.MATERIAL)
    testImplementation (TestDependencies.JUNIT)
    androidTestImplementation (TestDependencies.EXT_JUNIT)
    androidTestImplementation (TestDependencies.ESPRESSO)
}