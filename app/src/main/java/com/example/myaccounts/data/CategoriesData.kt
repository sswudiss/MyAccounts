package com.example.myaccounts.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myaccounts.R


data class CategoriesData(
    val drawable: ImageVector, // 圖示（可選）
    val text: String, //名稱（必填）
//    val colors:Int,  //顏色（可選）
//    val orderNum:Int //訂單號碼（可選，用於重新排序類別）
)

val categories = listOf(
    Icons.Default.MonetizationOn to "money",
    Icons.Default.FoodBank to "Bank",
    Icons.Default.MonetizationOn to "money",
    Icons.Default.FoodBank to "Bank",
    Icons.Default.MonetizationOn to "money",
    Icons.Default.FoodBank to "Bank"

).map { CategoriesData(it.first, it.second) }