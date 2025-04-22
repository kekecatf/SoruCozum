plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.sinavavhazirliguygulama"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sinavavhazirliguygulama"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
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
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material Icons (Dolu ve Outline versiyonları)
    implementation ("androidx.compose.material:material-icons-core")
    implementation ("androidx.compose.material:material-icons-extended")

    // Navigasyon bileşeni
    implementation ("androidx.navigation:navigation-compose:2.7.5")

    // Splash Screen API
    implementation ("androidx.core:core-splashscreen:1.0.1")

    // Görüntü yükleme kütüphanesi (isteğe bağlı)
    implementation ("io.coil-kt:coil-compose:2.5.0")

    // DataStore (Tercihler için)
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Room Database (Yerel veritabanı için)
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    // Kodu derlemek için kapt kullanıyorsanız, bu satırı ekleyin:
    // kapt 'androidx.room:room-compiler:2.6.1'
    // Alternatif olarak ksp kullanıyorsanız, bu satırı ekleyin:
    // ksp 'androidx.room:room-compiler:2.6.1'

    // ViewModel composable entegrasyonu
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Kotlin Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Test bağımlılıkları
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")

    // Debug bağımlılıkları
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    implementation ("com.google.code.gson:gson:2.10.1")
}