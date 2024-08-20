package com.smtersoyoglu.calculatorapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC","0", ".", "="
)


@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier) {
        Column (modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End)
        {
            Spacer(modifier = Modifier.height(80.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = equationText.value ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.padding(16.dp),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            Text(text = resultText.value?:"",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 60.sp,
                    textAlign = TextAlign.End),
                maxLines = 2,
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                thickness = 5.dp
            )

            Spacer(modifier = Modifier.height(50.dp))

            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(buttonList) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    })
                }
            }

        }

    }

}

@Composable
fun CalculatorButton(btn : String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = getColor(btn),
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        )
        {
            Text(text = btn,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.animateContentSize()
            )

        }

    }

}


fun getColor(btn:String) : Color {
    return when (btn) {
        "C", "AC" -> Color(0xFFE57373)
        "(", ")" -> Color(0xFF757575)
        "/", "*", "-", "+", "=" -> Color(0xFF4CAF50)
        else -> Color(0xFF2196F3)
    }
}
