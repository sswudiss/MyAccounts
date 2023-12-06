package com.example.myaccounts.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondaryContainer

/**
 * 收入統計 income statistics
 */

/**
 * 收入統計圖 Income statistics chart
 */


/**
 * 支出統計 Spending Statistics
 */

/**
 * 支出統計圖 Expenditure chart
 */


/**
 * 帳戶統計- Account Statistics
 */


/**
 * 類別統計- Category Statistics
 */


/**
 * 柱狀比例圖
 */
/*
@Composable
fun Histogram(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(26.dp)
    ) {
        LazyRow(
            modifier
                .height(180.dp)
                .background(MaterialTheme.colorScheme.outlineVariant),
            verticalAlignment = Alignment.Bottom
        ) {
            items(year2022) {
                Columnar(monthlyData = it)
                //間隔
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
}


@Composable
fun Columnar(
//    @StringRes text: Int,
    monthlyData: MonthlyData
) {
    val largestValue = year2022.maxOf { it.amount }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${monthlyData.amount}", fontSize = 10.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(
                    if (year2022.indexOf(monthlyData) % 2 == 0) {
                        md_theme_light_primary
                    } else md_theme_light_secondaryContainer
                )
                .width(29.dp)
                .height((200 * monthlyData.amount / largestValue).dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = monthlyData.month)
    }
}


@Preview
@Composable
private fun HistogramPrev() {
    Histogram()
}*/
