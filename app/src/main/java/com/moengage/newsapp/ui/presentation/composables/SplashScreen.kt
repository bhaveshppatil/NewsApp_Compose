package com.moengage.newsapp.ui.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moengage.newsapp.R
import com.moengage.newsapp.ui.theme.NewsAppTheme
import com.moengage.newsapp.ui.theme.primaryDark
import com.moengage.newsapp.utils.ContentDescription
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onLetsGoClicked: () -> Unit
) {
    var textIndex by remember { mutableIntStateOf(0) }
    var textToDisplay by remember { mutableStateOf("") }
    val texts = listOf(
        stringResource(id = R.string.tagline),
    )

    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[textIndex]
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(150)
            }
            // Stop the loop after one iteration
            break
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_mo),
            contentDescription = ContentDescription.SplashImage,
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = textToDisplay,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = primaryDark
        )
        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                onLetsGoClicked.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Let's Go")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    NewsAppTheme {
        SplashScreen(onLetsGoClicked = {})
    }
}