package com.test.nopstation_cart.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.nopstation_cart.R
import com.test.nopstation_cart.models.cart.OrderTotals

@Composable
fun FinalAmountBox(
    orders: OrderTotals,
    loader: Boolean,
    onClickListener: () -> Unit
) {

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(200.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        if (loader) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(color = colorResource(id = R.color.middle_color))
            }
        } else {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(title = "Sub-Total:", amount = orders.subTotal ?: "$0.00")
            TextField(title = "Shipping:", amount = orders.shipping ?: "$0.00")
            TextField(title = "Tax:", amount = orders.tax ?: "$0.00")
            TextField(title = "Total:", amount = orders.orderTotal ?: "$0.00", mode = "bold")
            TextField(
                title = "You Will Earn :",
                amount = "${orders.willEarnRewardPoints} Points",
                mode = "small&faded"
            )
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(40.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(gradientColor(), shape = RoundedCornerShape(4.dp))
                    .clickable { onClickListener() },
                contentAlignment = Alignment.Center
            )
            {
                Text(text = "Confirm", color = colorResource(id = R.color.white))
            }
        }

    }
}

@Composable
fun TextField(title: String, amount: String, mode: String = "default") {
    lateinit var style: FontWeight
    var textSize = 2.sp
    var textColor = colorResource(id = R.color.black)

    when (mode) {
        "small&faded" -> {
            style = FontWeight.Normal
            textSize = 12.sp
            textColor = colorResource(id = R.color.stroke)
        }

        "bold" -> {
            style = FontWeight.Bold
            textSize = 16.sp
        }

        "extraBold" -> {
            style = FontWeight.ExtraBold
            textSize = 18.sp
        }

        else -> {
            style = FontWeight.Normal
            textSize = 14.sp
        }
    }

    Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(.5f),
            textAlign = TextAlign.Start,
            fontWeight = style,
            fontSize = textSize,
            color = textColor
        )
        Text(
            text = amount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            textAlign = TextAlign.End,
            fontWeight = style,
            fontSize = textSize,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun Dekhi() {
    Surface {
        //FinalAmountBox(orders = OrderTotal)
    }
}