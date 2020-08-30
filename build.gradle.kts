// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val kotlinVersion: String by extra { "1.3.11" }

    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }


    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath(kotlin("gradle-plugin", version = "1.3.70"))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
        //   classpath("com.google.gms:google-services:4.3.3")
        //  classpath("io.fabric.tools:gradle:1.31.2")
        //  classpath("com.google.firebase:perf-plugin:1.3.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
