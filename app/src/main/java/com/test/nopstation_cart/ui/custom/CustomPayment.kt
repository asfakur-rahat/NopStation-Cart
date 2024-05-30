package com.test.nopstation_cart.ui.custom


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.nopstation_cart.R.*

@Composable
fun PaymentMethod() {
    val isCheckedCheck = remember { mutableStateOf(true) }
    val isCheckedCard = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = colorResource(id = color.stroke))
        ) {
            Row(modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                GolButton(
                    isChecked = isCheckedCheck.value,
                    onCheckedChange = { isCheckedCheck.value = true
                        isCheckedCard.value = false
                    }
                )
                Image(painter = painterResource(id = drawable.check), contentDescription = "cash",Modifier.padding(start = 10.dp))
                Text(text = "Check / Money Order", modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = colorResource(id = color.stroke))
        ) {
            Row(modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                GolButton(
                    isChecked = isCheckedCard.value,
                    onCheckedChange = { isCheckedCheck.value = false
                        isCheckedCard.value = true
                    }
                )
                Image(painter = painterResource(id = drawable.credit), contentDescription = "cash",Modifier.padding(start = 10.dp))
                Text(text = "Credit Card", modifier = Modifier.padding(start = 8.dp))
            }

        }
    }
}


@Composable
fun GolButton(
    isChecked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(width = 25.dp, height = 25.dp)
            .clip(shape = RoundedCornerShape(12.5.dp))
            .border(1.5.dp, colorResource(id = color.stroke), shape = RoundedCornerShape(12.5.dp))
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                imageVector = ImageVector.vectorResource(id = drawable.ic_check),
                contentDescription = "checked"
            )
        }
    }
}

@Preview
@Composable
private fun DekhiEktu() {
    Surface() {
        PaymentMethod()
    }
}