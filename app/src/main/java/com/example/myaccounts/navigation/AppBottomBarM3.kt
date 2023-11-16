package com.example.myaccounts.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AppBottomBarM3(
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
            BottomAppBar(  //使用材料設計3
            ) {
                NavigationBar {
                    bottomBarItems.forEach {
                        NavigationBarItem(
                            selected = bottomBarCurrentScreen == it,
                            onClick = { onClick(it) },
                            icon = {
                                Icon(
                                    imageVector = it.icon, contentDescription = null,
//                        tint = if (it.route == "Home")  Color.Cyan else Color.Green
                                )
                            },
                            /*
                                colors = NavigationBarItemDefaults.colors(
                                indicatorColor = if (it.route == "Home")MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                            */
                            label = { Text(text = it.route) }  //在圖標下面顯示文字
                        )
                    }
                }
            }
        }
    )
}
