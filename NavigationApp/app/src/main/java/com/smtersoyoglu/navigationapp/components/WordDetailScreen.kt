package com.smtersoyoglu.navigationapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smtersoyoglu.navigationapp.data.getWordList
import com.smtersoyoglu.navigationapp.ui.theme.teal_650

@Composable
fun WordDetailScreen( navController: NavController,wordId: Int) {
    val word = getWordList().find { it.id == wordId }

    word?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Learn This Word",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = ButtonDefaults.buttonColors(teal_650).containerColor
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(96.dp))
            Image(
                painter = painterResource(id = word.imageUrl),
                contentDescription = word.english,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = word.translation,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E4A59)
                )
            )

            Text(
                text = word.english,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE5C265)
                )
            )

            Text(
                text = word.sentence,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7B8A97)
                ),
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { navController.popBackStack()},
                colors = ButtonDefaults.buttonColors(teal_650),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(8.dp)
                ) {
                Text(
                    text = "Learned",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun WordDetailScreenPreview() {
    //WordDetailScreen(navController = NavController(LocalContext.current), wordId = 3)
}