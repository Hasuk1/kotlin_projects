plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.example.mylibrary"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
  buildFeatures {//Это очень важно, как и composeOptions
    //без этого говна приложение вылетало!
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.10"
  }
}

dependencies {
  val navVersion = "2.7.7"

  implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
  implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
  implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
  androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
  implementation("androidx.navigation:navigation-compose:$navVersion")

  implementation("androidx.compose.ui:ui:1.6.2")
  implementation("androidx.compose.ui:ui-tooling-preview:1.6.2")
  implementation("androidx.compose.material:material:1.6.2")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.11.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}