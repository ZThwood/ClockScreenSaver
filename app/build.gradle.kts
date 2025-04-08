plugins {
    // libs.plugins.android.application表示这是一个应用程序模块
    alias(libs.plugins.android.application)
    // kotlin 必须
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.clockscreensaver"
    compileSdk = 35

    // 对项目的更多细节进行配置
    defaultConfig {
        applicationId = "com.example.clockscreensaver"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        // testInstrumentationRunner用于在当前项目中启用JUnit测试
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // 通常只会有两个子闭包：一个是debug，一个是release
    buildTypes {
        // 通常只会有两个子闭包：一个是debug，一个是release
        release {
            // 代码混淆
            isMinifyEnabled = false
            // 混淆规则
            proguardFiles(
                // 通用规则， 一般在<Android SDK>/tools/proguard目录下
                getDefaultProguardFile("proguard-android-optimize.txt"),
                // 针对项目的特有规则 在同级目录
                "proguard-rules.pro"
            )
        }
        //  debug闭包用于指定生成测试版安装文件的配置 可忽略不写
        debug {

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
    }
}


// 指定当前项目所有的依赖关系， 据需求选择合适的依赖配置，可以优化构建性能和 APK 大小
dependencies {
    // implementation 声明主项目依赖
    implementation(libs.androidx.core.ktx)  // AndroidX Core KTX 扩展
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle KTX 扩展,管理 UI 组件的生命周期，避免内存泄漏

    /* 当你引入 Compose BOM 后，所有 Compose 相关库（如 androidx.compose.ui、androidx.compose.material3 等）的版本会自动从 BOM 中获取。
    你不再需要为每个 Compose 库单独指定版本号，避免版本不一致或冲突 */
    implementation(platform(libs.androidx.compose.bom)) // Compose BOM（Bill of Materials）, 用于管理 Jetpack Compose 相关库的版本。它的作用是 统一管理 Compose 相关库的版本，避免版本冲突。

    implementation(libs.androidx.activity.compose) // Compose Activity 支持 用于在 Activity 中使用 Compose 构建 UI
    implementation(libs.androidx.ui) // Compose UI 核心, 提供了构建 Compose UI 的基本组件和功能。
    implementation(libs.androidx.ui.graphics) // Compose 图形支持, 用于在 Compose 中绘制自定义图形或应用样式
    implementation(libs.androidx.ui.tooling.preview) // Compose 预览工具支持, 用于在 Android Studio 中实时预览 Compose UI
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx) // Material 3 设计库(Google 最新一代的设计语言), 用于构建符合 Material Design 3 标准的 UI 组件

    // testImplementation 声明单元测试依赖
    testImplementation(libs.junit)

    // androidTestImplementation 声明仪器测试依赖 这些依赖库仅在与 Android 设备相关的测试中可用，不会打包到最终的 APK 中
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // debugImplementation 声明调试模式依赖
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}