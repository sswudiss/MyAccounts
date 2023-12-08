package com.example.myaccounts.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myaccounts.navigation.Screen


/**
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transactions(
    navController: NavHostController,
    viewModel: TransactionViewModel = hiltViewModel()

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val transactionList by viewModel.transactions
        val options = listOf("All", "Expense", "Income")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Transactions", fontSize = 20.sp)

            ExposedDropdownMenuBox(
                expanded = viewModel.transactionType.value.isExpanded,
                onExpandedChange = {
                    viewModel.onEvent(TransactionsEvent.OnExpandedChange)
                },
                modifier = Modifier.width(140.dp)
            ) {
                /*TextField(
                    readOnly = true,
                    value = viewModel.transactionType.value.selectedOption,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewModel.transactionType.value.isExpanded
                        )
                    },
                    shape = RoundedCornerShape(16.dp)
                )*/

                //..........................
                ExposedDropdownMenu(
                    expanded = viewModel.transactionType.value.isExpanded,
                    onDismissRequest = {
                        viewModel.onEvent(TransactionsEvent.OnDismissRequest)
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption) },
                            onClick = {
                                viewModel.onEvent(
                                    TransactionsEvent.ChangeSelectedOption(
                                        selectionOption
                                    )
                                )
                                viewModel.onEvent(TransactionsEvent.OnDismissRequest)
                            }
                        )
                    }
                }
                //..........................

                var state by remember { mutableStateOf(false) }

                Row(modifier = Modifier.selectableGroup()) {
                    RadioButton(
                        selected = !state, //是否選擇該單選按鈕
                        onClick = { state = true },
                        modifier = Modifier.semantics { contentDescription = "Option 1" },
                        enabled = true
                    )

                    RadioButton(
                        selected = state,
                        onClick = { state = true },
                        modifier = Modifier.semantics { contentDescription = "Option 2" },
                        enabled = true
                    )

                    RadioButton(
                        selected = state,
                        onClick = { state = true },
                        modifier = Modifier.semantics { contentDescription = "Option 3" },
                        enabled = true
                    )
                }



            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (transactionList.list.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No recent transactions..",
                    color = Color(0xFFD16C97),
                )
            }

        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    8.dp, 0.dp, 8.dp, 64.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactionList.list) {
                    TransactionCard(transaction = it) {
                        navController.navigate(Screen.TransactionDetails.withArgs(it.id.toString()))
                    }
                }
            }
        }
    }
}