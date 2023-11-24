package com.example.myaccounts.utils

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

//視窗狀態實用程式
//新增 material3-window-size-class 依附元件至模組 build.gradle 檔案
//https://developer.android.com/courses/pathways/android-basics-compose-unit-4-pathway-3?hl=zh-tw

/**
 * Information about the posture of the device
 * 有關設備姿態的信息
 * DevicePosture 設備狀態
 */
sealed interface DevicePosture {
    //正常姿勢
    object NormalPosture : DevicePosture

    //書本姿勢
    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    //分離
    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}


@OptIn(ExperimentalContracts::class)
fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
}

/**
 * Different type of navigation supported by app depending on device size and state.
 * 應用程式支援不同類型的導航，具體取決於設備大小和狀態。
 * NavigationType 導航類型
 */
enum class AppNavigationType {
    //底部導航欄、側邊導航欄、導航抽屜
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Different position of navigation content inside Navigation Rail, Navigation Drawer depending on device size and state.
 * 根據設備尺寸和狀態，導航列、導航抽屜內導航內容的不同位置。
 * AppNavigationContentPosition 導航內容位置
 */
enum class AppNavigationContentPosition {
    TOP, CENTER
}

/**
 * App Content shown depending on device size and state.
 * 顯示的應用程式內容取決於裝置尺寸和狀態。
 * ContentType 內容類型
 */
enum class AppContentType {
    //單面板、雙面板
    SINGLE_PANE, DUAL_PANE
}

