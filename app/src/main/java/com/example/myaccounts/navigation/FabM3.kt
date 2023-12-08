package com.example.myaccounts.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.myaccounts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabM3(
    navBackStackEntry: NavBackStackEntry?,
    showBottomSheet: MutableState<Boolean>,
    fabState: MutableState<Boolean>,
    navigationToTopLevelDestination: (AppDestination) -> Unit,
    sheetState: SheetState,
    scope: CoroutineScope
) {
    AnimatedVisibility(
        visible = fabState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = fadeOut(targetAlpha = 0f),
        content = {
            FloatingActionButton(
                onClick = {
                    when (navBackStackEntry?.destination?.route) {
                        "OVERVIEW" -> {
                            showBottomSheet.value = true
                        }

                        "TRANSACTION_DETAILS" -> {
                            navigationToTopLevelDestination(ADD_TRANSACTION)
                        }

                        "ACCOUNT_SCREEN" -> {
                            navigationToTopLevelDestination(ADD_ACCOUNT)
                        }
                    }
                },
                containerColor = when (navBackStackEntry?.destination?.route) {
                    "OVERVIEW" -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.primaryContainer
                }
            ) {
                Icon(Icons.Filled.Add, "Add")
                AppModalBottomSheet(
                    currentBackStack = navBackStackEntry,
                    sheetState = sheetState,
                    scope = scope,
                    showBottomSheet = showBottomSheet,
                    navigationToTopLevelDestination = navigationToTopLevelDestination
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalBottomSheet(
    currentBackStack: NavBackStackEntry?,
    sheetState: SheetState,
    scope: CoroutineScope,
    showBottomSheet: MutableState<Boolean>,
    navigationToTopLevelDestination: (AppDestination) -> Unit
) {
    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState
        ) {
            when (currentBackStack?.destination?.route) {
                "OVERVIEW" -> {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.padding(6.dp))
                        //計劃金額
                        Button(onClick = {
                            navigationToTopLevelDestination(BUDGET)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet.value = false
                                }
                            }
                        }) {
                            Icon(
                                imageVector = BUDGET.selectedIcon,
                                contentDescription = stringResource(id = R.string.budget_amount)
                            )
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(text = stringResource(id = R.string.budget_amount))
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        //賬號轉移
                        Button(onClick = {
                            navigationToTopLevelDestination(ACCOUNT_TRANSFER)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet.value = false
                                }
                            }
                        }) {
                            Icon(
                                imageVector = ACCOUNT_TRANSFER.selectedIcon,
                                contentDescription = stringResource(id = R.string.account_transfer)
                            )
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(text = stringResource(id = R.string.account_transfer))
                        }
                    }
                }
            }
        }
    }
}