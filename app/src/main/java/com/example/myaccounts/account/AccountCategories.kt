package com.example.myaccounts.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myaccounts.data.categories

//- 建立類別- Create a category
//- 編輯類別- Edit a category
//- 刪除類別- Delete category
//- 重新排序類別- Reorder categories


/**
 * 賬戶類別 AccountCategories
 */
@Composable
fun AccountCategoriesRow(
    modifier: Modifier = Modifier,
) {
    val selected = rememberSaveable { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { item ->
            AccountCategories(drawable = item.drawable, text = item.text, selected = selected)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCategories(
    modifier: Modifier = Modifier,
    drawable: ImageVector,
    text: String,
    selected: MutableState<Boolean>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilterChip(
            selected = false,
            label = { Text(text = text) },
            leadingIcon = if (selected.value) {
                {
                    Icon(
                        imageVector = drawable,
                        contentDescription = null,
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
            onClick = {
              selected.value =  true
            }
        )
    }
}