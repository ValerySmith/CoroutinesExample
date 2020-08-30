import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    // id("com.google.gms.google-services")
    // id("io.fabric")
    // id("com.google.firebase.firebase-perf")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

// apply(plugin = "com.google.gms.google-services")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
       // sourceCompatibility = "1.8"
       // targetCompatibility = "1.8"
    }
}

val versionMajor = 0
val versionMinor = 1
val versionFix = 5

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")
    defaultConfig {
        applicationId = "deepvision.cear.my10"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = versionMajor * 1000000 + versionMinor * 1000 + versionFix
        versionName = "$versionMajor.$versionMinor.$versionFix"
    }

    compileOptions {

    }

    // Use debug keystore in project for signing
   /* signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("app/debug.p12")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }*/

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("staging") {
            isDebuggable = true
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-dev"
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    // Use debug keystore in project for signing
  /*  signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("app/debug.p12")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }*/

    dataBinding {
        isEnabled = true
    }
    lintOptions {
        isAbortOnError = true
        isWarningsAsErrors = true
    }
    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(kotlin("stdlib"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    val navigationVersion = "2.2.0-rc04"
    // Android
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.exifinterface:exifinterface:1.2.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.biometric:biometric:1.0.1")
   // implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

    // Android.UI
    implementation("com.google.android.material:material:1.3.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")

    val composeVersion = "0.1.0-dev01"
    implementation("androidx.compose:compose-runtime:$composeVersion")
    kapt("androidx.compose:compose-compiler:$composeVersion")
    implementation("androidx.ui:ui-layout:$composeVersion")
    implementation("androidx.ui:ui-android-text:$composeVersion")
    implementation("androidx.ui:ui-text:$composeVersion")
    implementation("androidx.ui:ui-material:$composeVersion")

    // Firebase
    implementation("com.google.firebase:firebase-analytics:17.2.2")
    implementation("com.crashlytics.sdk.android:crashlytics:2.10.1")
    implementation("com.google.firebase:firebase-perf:19.0.5")

    // Room components
    val roomVersion = "2.2.3"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Lifecycle components
    val lifecycleVersion = "2.1.0"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha06")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // ViewModel Kotlin support
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // Coroutines
    val coroutinesVersion = "1.3.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Images loader
    val glideVersion = "4.10.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    // Timber logger
    implementation("com.jakewharton.timber:timber:4.7.1")

    // CryptoPrefs
    //  implementation("com.github.AndreaCioccarelli:CryptoPrefs:1.1.1")

    // DI Koin
    val koinVersion = "2.1.3"
    implementation("org.koin:koin-androidx-fragment:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")
    implementation("org.koin:koin-androidx-scope:$koinVersion")
    implementation("org.koin:koin-androidx-ext:$koinVersion")

    // Permissions
    implementation("com.github.fondesa:kpermissions:3.1.2")

    // Serialization
    implementation("com.google.code.gson:gson:2.8.6")

    // Tooltip
    //  implementation("com.github.sephiroth74:android-target-tooltip:2.0.4")

    // Retrofit
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}
