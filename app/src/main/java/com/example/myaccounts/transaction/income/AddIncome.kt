package com.example.myaccounts.transaction.income

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myaccounts.transaction.AddTransactionEvent
import com.example.myaccounts.transaction.AddTransactionViewModel

@Composable
fun AddIncome(
    navController: NavHostController,
    addTransactionViewModel: AddTransactionViewModel = hiltViewModel(),
//    noAmount: Boolean
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(24.dp, 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            value = addTransactionViewModel.title.value.text,
            onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnteredTitle(it)) },
            placeholder = {
                Text(
                    text = addTransactionViewModel.title.value.hint,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            label = {
                Text(
                    //TODO  Text(stringResource(R.string)
                    "Title.. 輸入標題"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "Amount 輸入金額",
            singleLine = true,
            onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnteredTitle(it)) },
            placeholder = {
                Text(
                    text = addTransactionViewModel.amount.value.hint,
                    modifier = Modifier.alpha(0.5f)
                )
            },
//            label = {
//                if (noAmount) {
////             TODO         Text(stringResource(R.string.no_amount))
//                    Text(text = "No amount")
//                } else {
////             TODO         Text(stringResource(R.string.enter_amount))
//                    Text(text = "Enter Amount")
//                }
//            },
//            isError = noAmount,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            /*           keyboardActions = KeyboardActions(
                           onNext = { addTransactionViewModel.checkEnterAmount() }
                       )*/
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "Tags 輸入標籤",
            onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnteredTitle(it)) },
            placeholder = {
                Text(
                    text = addTransactionViewModel.tags.value.hint,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            label = {
                Text(
                    //TODO  Text(stringResource(R.string)
                    "Tags 輸入標籤"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "Type a note.. 輸入註釋",
            onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnteredTitle(it)) },
            placeholder = {
                Text(
                    text = addTransactionViewModel.note.value.hint,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            label = {
                //TODO  Text(stringResource(R.string)
                Text("Type a note.. 輸入註釋")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                addTransactionViewModel.onEvent(
                    AddTransactionEvent.SaveTransaction(
                        context,
                        navController
                    )
                )
            }
        ) {
            //TODO  Text(stringResource(R.string)
            Text(text = "SAVE")
        }
    }
}