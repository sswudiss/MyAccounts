package com.example.myaccounts.data

data class MonthlyData(
    val month: String,
    val amount: Double
)

val year2022 = mutableListOf(
    MonthlyData(month = "Jan", amount = 2000.05),
    MonthlyData(month = "Feb", amount = 3000.55),
    MonthlyData(month = "Mar", amount = 4301.5),
    MonthlyData(month = "Apr", amount = 1236.5),
    MonthlyData(month = "May", amount = 2601.0),
    MonthlyData(month = "Jun", amount = 1904.5),
    MonthlyData(month = "Jul", amount = 1289.5),
    MonthlyData(month = "Aug", amount = 1854.3),
    MonthlyData(month = "Sep", amount = 1118.6),
    MonthlyData(month = "Oct", amount = 2117.8),
    MonthlyData(month = "Nov", amount = 13210.5),
    MonthlyData(month = "Dec", amount = 1900.5),
)
