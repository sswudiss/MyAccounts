package com.example.myaccounts.transaction.add_transaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTransaction(
    navHostController: NavHostController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
) {
    BackHandler {
        viewModel.onEvent(AddEditTransactionEvent.OpenDialog)
    }
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
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.OpenDialog)

                },
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }

            Text(text = "Transactions", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(36.dp))
        }


        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFD16C97))
                    .padding(24.dp, 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = viewModel.title.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredTitle(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.title.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),

                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.amount.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredAmount(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.amount.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Decimal
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExposedDropdownMenuBox(
                    expanded = viewModel.transactionType.value.isExpanded,
                    onExpandedChange = {
                        viewModel.onEvent(AddEditTransactionEvent.OnExpandedChange)
                    },
                    modifier = Modifier.width(280.dp)
                ) {

                    TextField(
                        placeholder = {
                            Text(
                                text = viewModel.transactionType.value.hint,
                                modifier = Modifier.alpha(0.5f)
                            )
                        },
                        readOnly = true,
                        value = viewModel.transactionType.value.selectedOption,
                        onValueChange = { },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = viewModel.transactionType.value.isExpanded
                            )
                        },

                        shape = RoundedCornerShape(16.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.transactionType.value.isExpanded,
                        onDismissRequest = {
                            viewModel.onEvent(AddEditTransactionEvent.OnDismissRequest)
                        }
                    ) {
                        transactionTypes.forEach { selectionOption ->
                            DropdownMenuItem(
                                text= {Text(text = selectionOption)},
                                onClick = {
                                    viewModel.onEvent(
                                        AddEditTransactionEvent.ChangeSelectedOption(
                                            selectionOption
                                        )
                                    )
                                    viewModel.onEvent(AddEditTransactionEvent.OnDismissRequest)
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.tags.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredTags(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.tags.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.note.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredNote(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.note.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(
                            AddEditTransactionEvent.SaveEditTransaction(
                                context,
                                navHostController
                            )
                        )
                    }
                ) {
                    Text(text = "SAVE")
                }
            }
        }
    }
}