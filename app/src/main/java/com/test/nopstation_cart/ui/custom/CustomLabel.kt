package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.nopstation_cart.R

@Composable
fun CustomLabel(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradientColor(), shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = text,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun gradientColor(): Brush {
    return Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.start_color),
            colorResource(id = R.color.middle_color),
            colorResource(id = R.color.end_color)
        )
    )
}

@Preview
@Composable
private fun CustomLabelPreview() {
    CustomLabel(text = "Hello World")
}
