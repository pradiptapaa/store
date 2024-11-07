plugins {
    id("com.android.library")
    kotlin("kapt") version "2.0.21"
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "priya.pradipta.store.data"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(project(":store-domain"))
    implementation(project(":common"))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.core.v150)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.koin.core)
}
