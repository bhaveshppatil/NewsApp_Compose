package com.moengage.newsapp.ui.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewsFilterView(
    onFilterClicked: (filterType: String) -> Unit
) {
    val listOfOptions = listOf(
        "Older to newer"
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(listOfOptions) { item ->
            TextButton(onClick = { onFilterClicked.invoke(item) }) {
                Text(text = item)
            }
        }
    }
}