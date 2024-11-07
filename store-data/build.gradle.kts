plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(project(":store-domain"))
    implementation(project(":common"))
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.koin.core)
}
