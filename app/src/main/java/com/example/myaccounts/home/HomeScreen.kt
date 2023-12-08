package com.example.myaccounts.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myaccounts.R

@Composable
fun HomeScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val overviewCardState by viewModel.overviewCardState

    Column{
        Card(
            shape = RoundedCornerShape(0.dp,0.dp,16.dp,16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = stringResource(id = R.string.total_balance),
                        fontSize = 16.sp
                    )
                    Text(
                        text = if (overviewCardState.totalBalance!! >= 0) {
                            "$${overviewCardState.totalBalance}"
                        } else {
                            "-$${-overviewCardState.totalBalance!!}"
                        },
                        fontSize = 40.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SummaryMiniCard(
                            color = Color(0xFF00CB51),
                            imageVector = Icons.Outlined.ArrowDownward,
                            heading = stringResource(id = R.string.income),
                            content = "+$${overviewCardState.income}"
                        )
                        SummaryMiniCard(
                            color = Color(0xFFCB0000),
                            imageVector = Icons.Outlined.ArrowUpward,
                            heading = stringResource(id = R.string.pay_for),
                            content = "-$${overviewCardState.expense}"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

//摘要迷你卡
@Composable
fun SummaryMiniCard(
    color: Color,
    imageVector: ImageVector,
    heading: String,
    content: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
//                .background(Color(0xFFFAD9E6))
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = heading,
                modifier = Modifier.size(40.dp),
                tint = color
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = heading,
                color = Color.White.copy(0.7f),
                fontSize = 16.sp,
            )
            Text(
                text = content,
                color = Color.White
            )
        }
    }
}