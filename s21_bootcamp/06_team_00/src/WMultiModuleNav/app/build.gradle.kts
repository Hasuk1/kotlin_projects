plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
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
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    kotlinCompilerExtensionVersion = "1.5.10"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}


dependencies {
  val composeVersion = "1.8.2"
  val navVersion = "2.7.7"

  implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
  implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
  implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
  androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
  implementation("androidx.navigation:navigation-compose:$navVersion")

  implementation("androidx.activity:activity-compose:$composeVersion")
  implementation("androidx.compose.ui:ui:1.6.2")
  implementation("androidx.compose.ui:ui-tooling-preview:1.6.2")
  implementation("androidx.compose.material:material:1.6.2")
  implementation("androidx.compose.material3:material3:1.2.0")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  implementation(project(":core:mylibrary"))
}