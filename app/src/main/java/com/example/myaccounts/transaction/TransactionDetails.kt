package com.example.myaccounts.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myaccounts.navigation.ADD_INCOME
import com.example.myaccounts.navigation.AppRoute.TRANSACTION_DETAILS

/**
 * Detail screen for a single account.
 * 詳細資訊畫面
 */

@Composable
fun TransactionDetails(
    navController: NavHostController,
    viewModel: TransactionDetailViewModel = hiltViewModel(),
    transactionId: Int

) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }

            Row {
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Transactions", fontSize = 20.sp)
            }
            Row {
                IconButton(onClick = {
                    viewModel.onEvent(
                        TransactionsDetailEvent.Delete(
                            navController,
                            id = transactionId
                        )
                    )
                }) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFD16C97))
                        .padding(24.dp, 32.dp),
                    verticalArrangement = Arrangement.Center,
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    Column {
                        Text(
                            text = "Title 標題",
                            color = Color.White.copy(0.7f),
                            )
                        viewModel.currTransaction.value.transaction?.let {
                            Text(
                                text = it.title,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column {
                        Text(
                            text = "Amount 金額",
                            color = Color.White.copy(0.7f),

                            )
                        Text(
                            text = "$${viewModel.currTransaction.value.transaction?.amount}",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column {
                        Text(
                            text = "Transaction Type 交易類型",
                            color = Color.White.copy(0.7f),

                            )
                        viewModel.currTransaction.value.transaction?.let {
                            Text(
                                text = it.transactionType,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column {
                        Text(
                            text = "Date 時間",
                            color = Color.White.copy(0.7f),

                            )
                        viewModel.currTransaction.value.transaction?.let {
                            Text(
                                text = it.date,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column {
                        Text(
                            text = "Note 筆記",
                            color = Color.White.copy(0.7f),

                            )
                        viewModel.currTransaction.value.transaction?.let {
                            Text(
                                text = it.note,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        navController.navigate(ADD_INCOME.route + "/${transactionId}" + "/${TRANSACTION_DETAILS}")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
