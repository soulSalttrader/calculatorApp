import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.compose.compiler)
}


kapt {
    correctErrorTypes = true
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("11")
    }
}

android {
    namespace = "com.example.calculatorApp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.calculatorApp"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.junitJupiterApi) // JUnit 5 API
    testRuntimeOnly(libs.junitJupiterEngine) // JUnit 5 engine for running tests
    testImplementation(libs.junitJupiterParams) // JUnit 5 parameterized tests
    testImplementation(libs.mockk) // Mocking library for Kotlin tests
    testImplementation(libs.kotestAssertions) // Kotest assertion
    implementation(libs.kotlin.reflect) // Enable reflection

    kapt(libs.hilt.android.compiler) // Annotation processor
    implementation(libs.hilt.android) // Core Dagger Hilt library
    implementation(libs.androidx.hilt.navigation.compose) // Dagger Hilt + Navigation + Compose
    implementation(libs.androidx.lifecycle.viewmodel.savedstate) // ViewModel + SavedStateHandle
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}