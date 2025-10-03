import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.parcelize)
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

    testImplementation(libs.junitJupiterApi) // Core JUnit 5 API (e.g., @Test, @Nested, etc.)
    testRuntimeOnly(libs.junitJupiterEngine) // JUnit 5 test engine needed to run Jupiter tests
    testImplementation(libs.junitJupiterParams) // Support for parameterized tests with @ParameterizedTest and @MethodSource, etc.
    testRuntimeOnly(libs.junitPlatformLauncher) // Enables test discovery and execution via the JUnit Platform launcher (needed for Gradle integration)
    testRuntimeOnly(libs.junitPlatformEngine) // Provides the test engine interface that all test engines (like Jupiter) implement
    testImplementation(libs.mockk) // Lightweight mocking library for Kotlin unit tests
    testImplementation(libs.kotestAssertions) // Kotest assertions for expressive and readable test validations (e.g., shouldBe, shouldThrow)

    implementation(libs.androidx.lifecycle.viewmodel.savedstate) // Enables retrieving SavedStateHandle in ViewModels for state restoration

    implementation(libs.kotlin.reflect) // Kotlin reflection library, required for advanced features like class introspection (used by frameworks like Hilt or Kotest)

    implementation(libs.hilt.android) // Core Dagger Hilt library for dependency injection
    kapt(libs.hilt.android.compiler) // Dagger Hilt annotation processor, generates code for dependency injection
    implementation(libs.androidx.hilt.navigation.compose) // Integration for using Hilt with Jetpack Compose navigation (e.g., hiltViewModel in composables)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}