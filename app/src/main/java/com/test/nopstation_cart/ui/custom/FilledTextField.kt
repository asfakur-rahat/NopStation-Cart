package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.nopstation_cart.R.*


@Composable
fun FilledTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    size: String = "Default"
) {
    var padding = 0.dp
    var textSize = 0.sp
    when (size) {
        "small" -> {
            padding = 4.dp
            textSize = 14.sp
        }

        else -> {
            padding = 8.dp
            textSize = 18.sp
        }
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding, start = 16.dp, end = 16.dp, bottom = padding)
            .background(color = Color.Transparent),
        label = {
            Text(
                modifier = Modifier.padding(start = 0.dp),
                text = hint,
                fontSize = textSize,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = color.white),
            unfocusedContainerColor = colorResource(id = color.white),
            focusedIndicatorColor = colorResource(id = color.stroke),
            unfocusedIndicatorColor = colorResource(id = color.stroke),
            unfocusedLabelColor = colorResource(id = color.stroke),
            focusedLabelColor = colorResource(id = color.category_color)
        ),
        trailingIcon = {
            if (size == "Default") {
                Icon(
                    imageVector = ImageVector.vectorResource(id = drawable.ic_drop),
                    contentDescription = "ok"
                )
            }
        }
    )
}

@Preview
@Composable
private fun UiPreview() {
    val lol = remember {
        mutableStateOf("")
    }
    FilledTextField(hint = "Hint",lol.value, {lol.value = it}, size = "small")
}