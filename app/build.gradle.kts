plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.graduationproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.graduationproject"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

        // FaceNet
        implementation("org.tensorflow:tensorflow-lite:2.10.0")
        implementation("org.tensorflow:tensorflow-lite-support:0.3.1")
        implementation("org.tensorflow:tensorflow-lite-gpu:2.10.0")

        // Firebase
        implementation("com.google.firebase:firebase-firestore:24.4.5")
        implementation("com.google.firebase:firebase-auth:22.3.1")
        implementation("com.google.firebase:firebase-appcheck:17.0.1")
        implementation("com.google.firebase:firebase-appcheck-interop:17.0.0") // ✅ هذا هو المطلوب

        // UI Libraries
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.constraintlayout:constraintlayout:2.2.1")

        // Image Loader
        implementation("com.github.bumptech.glide:glide:4.15.1")
        annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    }

