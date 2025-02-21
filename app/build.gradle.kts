plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yunpnzr.mynoteapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yunpnzr.mynoteapp"
        minSdk = 27
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
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions+="env"
    productFlavors {
        create("dev"){
            dimension = "env"
            buildConfigField("String", "BASE_URL_BMKG", "\"https://api.bmkg.go.id/publik/\"")
            buildConfigField("String", "BASE_URL_SHALAT", "\"https://api.myquran.com/v2/\"")
        }
        create("env"){
            dimension = "env"
            buildConfigField("String", "BASE_URL_BMKG", "\"https://api.bmkg.go.id/publik/\"")
            buildConfigField("String", "BASE_URL_SHALAT", "\"https://api.myquran.com/v2/\"")  
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //gson
    implementation(libs.gson)

    //room
    implementation(libs.room.ktx)
    ksp(libs.room.complier)

    //koin
    implementation(libs.koin)

    //location
    implementation(libs.location)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}