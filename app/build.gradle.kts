plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.devikiran.newsy"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.devikiran.newsy"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.devikiran.newsy.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "clear_text_config", "false")
        }

        debug {
            isMinifyEnabled = false
            resValue("string", "clear_text_config", "true")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    //paging
    testImplementation(libs.androidx.paging.testing)

    //test2
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)

    //mockWebServer
    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockwebserver)

    //mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.core.testing)
    implementation(libs.okhttp)
    implementation(libs.okio)


    implementation(libs.androidx.adaptive.android)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation (libs.androidx.annotation)

    //kotlin Coroutines
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit2.converter.gson)

    //paging
    implementation (libs.paging)

    //Dagger Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    // For instrumented tests.
    androidTestImplementation(libs.hilt.test)
    // ...with Kotlin.
    kspAndroidTest(libs.hilt.test)

    //Navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    //Coil
    implementation(libs.coil.compose)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    //Room DataBase
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

}