package com.example.myaccounts.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabM2(
    currentBackStack: NavBackStackEntry?,
    sheetState: SheetState,
    scope: CoroutineScope,
    showBottomSheet: MutableState<Boolean>,
    onClick: (AppDestination) -> Unit,
    fabState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = fabState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = fadeOut(targetAlpha = 0f),
        content = {
            FloatingActionButton(
                onClick = { showBottomSheet.value = true },
                backgroundColor = if (currentBackStack?.destination?.route == "Home") MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.primaryContainer
//
            ) {
                Icon(Icons.Filled.Add, "Add")

                if (showBottomSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet.value = false
                        },
                        sheetState = sheetState
                    ) {
                        // Sheet content

                        if (currentBackStack?.destination?.route == "Home") {
                            //............
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ElevatedButton(onClick = {
                                    onClick(PlanAmount)  //計劃金額
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = PlanAmount.icon,
                                        contentDescription = "Add planned payment"
                                    )
                                    Text("ADD PLANNED PAYMENT")
                                }

                                Spacer(modifier = Modifier.padding(6.dp))

                                Button(onClick = {
                                    onClick(AccountTransfer) //賬戶轉移
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }

                                }) {
                                    Icon(
                                        imageVector = AccountTransfer.icon,
                                        contentDescription = "Account transfer"
                                    )
                                    Text("ACCOUNT TRANSFER")
                                }
                            }
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 60.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(onClick = {
                                    onClick(Income)
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = Income.icon,
                                        contentDescription = "Add income"
                                    )
                                    Text("ADD INCOME")
                                }

                                Spacer(modifier = Modifier.padding(18.dp))

                                Button(onClick = {
                                    onClick(PayFor)
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = PayFor.icon,
                                        contentDescription = "pay for"
                                    )
                                    Text("PAY  FOR")
                                }
                            }
                            //..........
                        } else
                        //.......
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = {
                                    onClick(AddAccount)
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = AddAccount.icon,
                                        contentDescription = "Add Account"
                                    )
                                    Text("ADD ACCOUNT")
                                }
                            }
                    }
                }
            }
        }
    )
}