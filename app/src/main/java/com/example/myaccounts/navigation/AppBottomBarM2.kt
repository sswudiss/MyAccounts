package com.example.myaccounts.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AppBottomBarM2(
    bottomBarItems: List<AppDestination>,
    onClick: (AppDestination) -> Unit,
    bottomBarCurrentScreen: AppDestination,
    bottomBarState: MutableState<Boolean>  //動畫需要的狀態
) {
    //使用動畫
    AnimatedVisibility(
        // 定義內容是否可見
        visible = bottomBarState.value,
        // 用於出現的動畫，預設在展開時淡入
        enter = slideInVertically(initialOffsetY = { it }),
        // 用於消失動畫，預設在縮小時淡出
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomAppBar(  //使用材料設計2
                // 材料設計2才有cutoutShape，添加到 BottomAppBar 的切口的形狀（在FAB周圍有空白）
                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)) //percent需小於100，否則AndroidRuntime
            ) {
                BottomNavigation {  //使用材料設計2
                    bottomBarItems.forEach {
                        BottomNavigationItem(  //使用材料設計2
                            selected = bottomBarCurrentScreen == it,
                            onClick = { onClick(it) },
                            icon = {
                                Icon(
                                    imageVector = it.icon, contentDescription = null,
                                )
                            },
                            label = { Text(text = it.route) }  //在圖標下面顯示文字
                        )
                    }
                }
            }
        }
    )
}
