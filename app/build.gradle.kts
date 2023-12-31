import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("maven-publish")
    id("kotlinx-serialization")
}

fun getSupabaseUrl(): String {
    return "https://dtpjecnnuqsayigslrvv.supabase.co"
}

fun getSupabaseKey(): String {
    return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR0cGplY25udXFzYXlpZ3NscnZ2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDI4MTY2NTksImV4cCI6MjAxODM5MjY1OX0.2wpn8jozDwql27bufP_uwcfhYPMx1WTJD8_gNWdp7_8"
}

android {
    namespace = "com.example.httpinspector"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "SUPABASE_URL", "\"${getSupabaseUrl()}\"")
        buildConfigField("String", "SUPABASE_KEY", "\"${getSupabaseKey()}\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Ok http
    val okHttpVersion = "4.11.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    //room
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // gson
    implementation("com.google.code.gson:gson:2.10.1")

    //Material Design
    implementation("androidx.compose.material3:material3:1.2.0-alpha10")

    //compose viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Nav Host
    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //supabase
    val supabase_version = "2.0.1"
    implementation(platform("io.github.jan-tennert.supabase:bom:$supabase_version"))
    implementation("io.github.jan-tennert.supabase:gotrue-kt")
    implementation("io.github.jan-tennert.supabase:functions-kt")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")

    //ktor
    val ktor_version = "2.3.7"
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.abdelrahmanmohamed728"
            artifactId = "http_inspector"
            version = "0.0.1-alpha"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}







