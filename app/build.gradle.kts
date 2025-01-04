plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Arayüz İçin
    implementation("androidx.compose.ui:ui-text-android:1.6.7")
    implementation("androidx.compose.foundation:foundation-android:1.6.7")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.x.x")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Sayfalar arası veri transferi(Nesne Tabanlı)
    implementation("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation ("androidx.compose.ui:ui:1.5.0") // UI toolkit
    implementation ("androidx.compose.material:material:1.5.0") // Material Design
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0") // Tooling preview
    implementation ("androidx.compose.foundation:foundation:1.5.0") // Foundation
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation ("androidx.activity:activity-compose:1.7.2")

    implementation ("com.mikepenz:iconics-core:5.3.1")
    implementation ("com.mikepenz:iconics-views:5.3.1")
    implementation ("com.mikepenz:iconics-compose:5.3.1")
    implementation ("com.mikepenz:material-design-iconic-typeface:2.2.0.5@aar")
}