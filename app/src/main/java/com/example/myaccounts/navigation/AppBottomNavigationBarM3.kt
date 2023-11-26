package com.example.myaccounts.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigationBarM3(
    selectedDestination: String,
    //導航至頂級目的地
    navigationToTopLevelDestination: (AppDestination) -> Unit,
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
//            BottomAppBar(  //使用材料設計3
//            ) {
                NavigationBar(modifier = Modifier.fillMaxWidth()) {
                    TOP_LEVEL_DESTINATIONS.forEach {
                        NavigationBarItem(
                            selected = selectedDestination == it.route,
                            onClick = { navigationToTopLevelDestination(it) },
                            icon = {
                                Icon(
                                    imageVector = it.selectedIcon,
                                    contentDescription = stringResource(id = it.iconTextId)
                                )
                            },
                            label = { Text(text = stringResource(id = it.iconTextId)) }  //在圖標下面顯示文字
                        )
                    }
                }
//            }
        }
    )
}
