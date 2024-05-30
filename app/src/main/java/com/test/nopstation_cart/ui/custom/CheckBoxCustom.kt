package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.nopstation_cart.R.*

@Composable
fun CheckBoxCustom(
    isChecked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .size(width = 20.dp, height = 20.dp)
            .clip(shape = RoundedCornerShape(2.dp))
            .border(1.dp, colorResource(id = color.stroke), shape = RoundedCornerShape(2.dp))
            .padding(start = 4.dp, top = 3.dp)
            .clickable { onCheckedChange(!isChecked) }
    ) {
        if (isChecked) {
            Icon(
                imageVector = ImageVector.vectorResource(id = drawable.ic_check),
                contentDescription = "checked"
            )
        }
    }
}

@Composable
fun getCheckBox() {
    val isChecked = remember {
        mutableStateOf(false)
    }
    CheckBoxCustom(isChecked.value, onCheckedChange = { isChecked.value = it })
}


@Preview
@Composable
private fun PreviewBox() {
    getCheckBox()
}