plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.navigation.safeargs)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myapplication"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.picasso)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // implementacion de fireBase
    implementation(platform("com.google.firebase:firebase-bom:34.14.1"))
    implementation("com.google.firebase:firebase-auth")

    // Credential Manager
    implementation("androidx.credentials:credentials:1.6.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.6.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.2.0")

    implementation("com.firebaseui:firebase-ui-auth:9.0.0")


    // para la base de datos

    implementation("com.google.firebase:firebase-firestore")


    // For standard XML Views (Activities/Fragments)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.fragment:fragment-ktx:1.8.9") // Needed for easy initialization
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}