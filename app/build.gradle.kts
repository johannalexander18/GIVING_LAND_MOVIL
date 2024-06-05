plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}


android {
    namespace = "com.example.givinglandv1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.givinglandv1"
        minSdk = 26
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    //IMPLEMENTACION PARA MOSTRAR LA CUADRICULA DE LOS ELEMENTOS "TARGETAS"
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    //IMPLEMENTACION PARA GESTIONAR LOS CICLOS DE VIDA EFICIENTEMENTE
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")


    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}