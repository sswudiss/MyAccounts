/*
package com.example.myaccounts.search

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    returnToHome: () -> Unit
) {
    // this is the text users enter
    var queryString by remember { mutableStateOf("") }

    // if the search bar is active or not
    var isActive by remember { mutableStateOf(false) }

    val contextForToast = LocalContext.current.applicationContext

    // previous search terms 之前的搜尋詞
    val historyItems = remember {
        mutableStateListOf("null")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            query = queryString,
            // this is called every time the user enters a new character 每次使用者輸入新字元時都會呼叫此函數
            onQueryChange = { queryString = it },

            //當使用者點擊鍵盤上的搜尋圖示時呼叫此函數
            onSearch = {
                isActive = false
                Toast.makeText(
                    contextForToast,
                    "Search: $queryString",
                    Toast.LENGTH_SHORT
                )
                    .show()
                historyItems.add(queryString) // add the current search term to the list 將目前搜尋詞加入到清單中
            },
            active = isActive,
            onActiveChange = { isActive = it },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                if (isActive)
                    IconButton(onClick = returnToHome) {
                        Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = null)
                    }
                else Icon(imageVector = Icons.Default.Search, contentDescription = null)

            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            },

            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                dividerColor = MaterialTheme.colorScheme.primary,
                inputFieldColors = TextFieldDefaults.colors()
            )
        ) {
            // this is a column scope
            // all the items are displayed vertically 所有項目均垂直顯示
            historyItems.forEach { historyItem ->
                Row(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 12.dp),
                        imageVector = Icons.Default.History, contentDescription = null
                    )
                    Text(text = historyItem)
                    //TODO 刪除單個和全部的搜索記錄，以及刪除圖標靠屏幕右邊
                }
            }
        }
    }
}
*/
