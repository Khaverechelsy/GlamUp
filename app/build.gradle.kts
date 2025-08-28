plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)


}

android {
    namespace = "com.example.glamup"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.glamup"
        minSdk = 24
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
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation("io.coil-kt:coil-compose:2.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.cloudinary:cloudinary-android:2.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

}
//plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
////    kotlin("android")
//    kotlin("plugin.compose") version "2.0.21"
//    alias(libs.plugins.google.gms.google.services)
////    id("com.google.gms.google-services") // fix: should be google-services, not google.service
//}
//
//android {
//    namespace = "com.example.glamup"
//    compileSdk = 36
//
//    defaultConfig {
//        applicationId = "com.example.glamup"
//        minSdk = 24
//        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.3"
//    }
//}
//
//dependencies {
//
//    // Core Android
//    implementation("androidx.core:core-ktx:1.10.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation("androidx.activity:activity-compose:1.7.2")
//
//    // Compose
//    implementation(platform("androidx.compose:compose-bom:2025.08.01"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material:material-icons-extended:1.5.1")
//
//    // Navigation
//    implementation("androidx.navigation:navigation-compose:2.9.3")
//
//    // Firebase (use KTX versions for Kotlin)
//    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
//    implementation("com.google.firebase:firebase-auth-ktx")
//    implementation("com.google.firebase:firebase-firestore-ktx")
//
//    // Coil for images
//    implementation("io.coil-kt:coil-compose:2.4.0")
//
//    // Retrofit & OkHttp
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
//    implementation(libs.firebase.auth.ktx)
//    implementation(libs.firebase.common.ktx)
//    implementation(libs.firebase.auth)
//    implementation(libs.firebase.database)
//    implementation(libs.firebase.firestore)
//
//    // Debug tooling
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
//
//    // Testing
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.01"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    implementation("androidx.compose.ui:ui:1.5.3")
//    implementation("androidx.core:core-ktx:1.10.1")
//    implementation("androidx.activity:activity-compose:1.7.2")
//    implementation("androidx.compose.material3:material3:1.2.0")
//    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
//}
//
//

//plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
//    id("com.google.gms.google-services") // Firebase
//}
//
//android {
//    namespace = "com.example.glamup"
//    compileSdk = 36
//
//    defaultConfig {
//        applicationId = "com.example.glamup"
//        minSdk = 24
//        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//
//    kotlinOptions {
//        jvmTarget = "11"
//    }
//
//    buildFeatures {
//        compose = true
//    }
//    // With Kotlin 2.0+, composeOptions is optional
//    // composeOptions {
//    //     kotlinCompilerExtensionVersion = "2.0.21"
//    // }
//}
//
//dependencies {
//    // Core Android
//    implementation("androidx.core:core-ktx:1.10.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation("androidx.activity:activity-compose:1.7.2")
//
//    // Compose (managed by BOM)
//    implementation(platform("androidx.compose:compose-bom:2025.08.01"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material:material-icons-extended")
//    implementation("androidx.navigation:navigation-compose:2.9.3")
//
//    // Firebase
////    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
////    implementation("com.google.firebase:firebase-auth-ktx")
////    implementation("com.google.firebase:firebase-firestore-ktx")
////    implementation("com.google.firebase:firebase-analytics-ktx")
//    implementation("io.coil-kt:coil-compose:2.4.0")
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
//    implementation(libs.firebase.auth)
//    implementation(libs.firebase.database)
//    implementation(libs.firebase.firestore.ktx)
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.01"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//}
