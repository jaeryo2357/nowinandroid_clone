package com.example.nowinandroid_clone.builcsrc

object Versions {
    const val versionName = "0.0.1"
    const val versionCodeBase = 1
    val versionCodeMoblie = versionCodeBase + 3 //?

    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21

    const val ktlint = "0.43.0"
}

object Libs {
    const val androidGradlePlugin =
        "com.android.tools.build:gradle:7.0.3"
    const val jdkDesugar =
        "com.android.tools:desugar_jdk_libs:1.1.5"

    const val junit = "junit:junit:4.13"

    const val material3 = "com.google.android.material:material:1.5.0-alpha05"

    object Accompanist {
        private const val version = "0.21.4-beta"
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$version"
    }

    object Protobuf {
        private const val version = "3.19.1"
        private const val plugin_version = "0.8.18"

        const val profoc = "com.google.protobuf:protoc:$version"
        const val profoc_kotlin = "com.google.protobuf:protobuf-kotlin-lite:$version"
        const val plugin = "com.google.protobuf:$plugin_version"
    }

    object KSP {
        const val version = "1.6.10-1.0.2"
        const val plugin = "com.google.devtools.ksp"
    }

    object DI {
        private const val version = "2.41"
        const val hilt_android = "com.google.dagger:hilt-android:$version"
        const val hilt_compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val hilt_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hilt_android_testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Kotlin {
        private const val version = "1.6.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.1"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1"

        object Coroutines {
            private const val version = "1.6.0"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Network {
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.3"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val dataStore = "androidx.datastore:datastore:1.0.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Compose {
            const val version = "1.2.0-alpha03"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val navigation = "androidx.navigation:navigation-compose:2.4.0-rc01"
            const val hilt_navigation_compose = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$version"

            object Material3 {
                const val snapshot = ""
                const val version = "1.0.0-alpha03"

                const val icon = "androidx.compose.material:material-icons-extended:${Compose.version}"
                const val material3 = "androidx.compose.material3:material3:$version"
            }
        }

        object Navigation {
            private const val version = "2.3.5"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            private const val version = "2.4.1"
            const val roomRuntime = "androidx.room:room-runtime:$version"
            const val roomCompiler = "androidx.room:room-compiler:$version"
            const val roomKtx = "androidx.room:room-ktx:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
            const val runner = "androidx.test:runner:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
            const val mockk = "io.mockk:mockk:1.12.1"

            const val turbin = "app.cash.turbine:turbine:0.7.0"
        }

        object Lifecycle {
            private const val version = "2.4.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }
    }
}