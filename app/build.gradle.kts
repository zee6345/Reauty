plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions { jvmTarget = JavaVersion.VERSION_11.toString() }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

configurations.all {
    resolutionStrategy.force("junit:junit:4.13.2")
}

dependencies {

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.test.ext:junit-ktx:1.1.2")
    // desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")


    // ktx, appCompat, material, constraint layout, viewpage, indicator
    implementation(libs.bundles.support.bundles)

    // jetpack
    // lifecycle, navigation, room, datastore, paging, workmanager
    implementation(libs.bundles.jetpack.bundles)

    // di
    // hilt
    implementation(libs.bundles.di.bundles)

    // network
    // retrofit, okhttp, gson
    implementation(libs.bundles.network.bundles)

    // image processing
    // glide
    implementation(libs.bundles.image.bundles)


    // logging
    // timber, orhanobut
    implementation(libs.bundles.log.bundles)

    // coroutines
    implementation(libs.bundles.coroutines.bundles)

    // date time
    // kotlinx-datetime
    implementation(libs.bundles.datetime.bundles)

    // util bundles
    // lottie, accompanist pager and indicator
    implementation(libs.bundles.util.bundles)

    // firebase
    // auth-ktx, realtime-db-ktx, cloud-storage-ktx, dynamic-link
    implementation(libs.bundles.firebase.bundles)

    // kapt compiler
    // room, hilt, glide, compose
    kapt(libs.bundles.kapt.bundles)

    implementation("com.github.ybq:Android-SpinKit:1.4.0")
    implementation ("gun0912.ted:tedbottompicker:2.0.1")
    implementation("pub.devrel:easypermissions:3.0.0")
    implementation("com.github.CanHub:Android-Image-Cropper:3.3.5")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")


    // unit testing
    // coroutine, mockwebserver, junit, androidx-test,
    // truth, turbine, mockito, mockito-kotlin, hilt
    testImplementation(libs.bundles.unit.testing.bundles)
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")

    // instrumented testing
    // junit-ext, espresso, truth, hilt,
    // room, workmanager, junit-compose,
    // ui-tooling, navigation, mockwebserver, hilt
    androidTestImplementation(libs.bundles.instrumented.testing.bundles)

    // kapt test and instrumented test
    // hilt
    kaptTest(libs.kapt.hilt)
    kaptAndroidTest(libs.kapt.instrumented.hilt)

}