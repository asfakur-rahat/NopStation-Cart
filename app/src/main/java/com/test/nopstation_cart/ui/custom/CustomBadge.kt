package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.nopstation_cart.R

@Composable
fun CustomBadge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    borderColor: Color = colorResource(id = R.color.badge_stroke),
    borderWidth: Float = 1f,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(20.dp)
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = CircleShape
            )
            .background(color = containerColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview
@Composable
private fun BadgePreview() {
    CustomBadge {
        Text(text = "1")
    }
}