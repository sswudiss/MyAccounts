package com.example.myaccounts.home

/**
 * 概述卡狀態
 */
data class OverviewCardState(
    val totalBalance: Long? = 0,
    val income :Long? = 0,
    val expense : Long? = 0
)