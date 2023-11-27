// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //聲明 KSP 插件
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}