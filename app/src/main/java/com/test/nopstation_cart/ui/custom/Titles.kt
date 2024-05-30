package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.nopstation_cart.R

@Composable
fun Titles(text: String) {
    Text(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .fillMaxWidth(),
        text = text,
        color = colorResource(id = R.color.black),
        fontSize = 14.sp
    )
}

@Preview
@Composable
private fun Ok() {
    Titles(text = "Hello")
}