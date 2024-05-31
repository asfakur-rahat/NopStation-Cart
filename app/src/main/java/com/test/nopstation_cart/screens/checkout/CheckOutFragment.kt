package com.test.nopstation_cart.screens.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.nopstation_cart.R.*
import com.test.nopstation_cart.ui.custom.CustomBadge
import com.test.nopstation_cart.ui.custom.CustomLabel
import com.test.nopstation_cart.ui.custom.FilledTextField
import com.test.nopstation_cart.ui.custom.FinalAmountBox
import com.test.nopstation_cart.ui.custom.PaymentMethod
import com.test.nopstation_cart.ui.custom.Titles
import com.test.nopstation_cart.ui.custom.getCheckBox
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class CheckOutFragment : Fragment(layout.fragment_check_out) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CheckOutScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CheckOutScreen() {

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(
                        brush = gradientColor()
                    ),
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = colorResource(id = color.white)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = colorResource(id = color.white)
                    ),
                    title = {
                        Text(text = "One Page Checkout")
                    },
                    actions = {
                        BadgedBox(
                            modifier = Modifier.padding(end = 16.dp),
                            badge = {
                                CustomBadge(
                                    containerColor = colorResource(id = color.category_bg_color),
                                    borderColor = colorResource(id = color.badge_stroke)

                                ) {
                                    Text(
                                        text = "9",
                                        fontSize = 12.sp,
                                        color = colorResource(id = color.black)
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(drawable.ic_checkout),
                                contentDescription = "Go to Cart",
                                tint = colorResource(id = color.white),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                )
            }
        ) {
            BodyContent(it)
        }
    }

    @Composable
    fun BillingAddress(it: Any) {
        CustomLabel(text = "Billing Address")
        Titles(text = "Address")
        FilledTextField(hint = "Existing Address")
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            getCheckBox()
            Text("Ship to the same address",Modifier.padding(start = 10.dp,top = 1.dp))
        }
        Titles(text = "Select A Billing Address")
        FilledTextField(hint = "New")
        FilledTextField(hint = "First Name :", "small")
        FilledTextField(hint = "Last Name :", "small")
        FilledTextField(hint = "Email :", "small")
        FilledTextField(hint = "Company :", "small")
        FilledTextField(hint = "Country :", "small")
        FilledTextField(hint = "State / Province :", "small")
        FilledTextField(hint = "Zip / Postal Code :", "small")
        FilledTextField(hint = "City :", "small")
        FilledTextField(hint = "Phone Number :", "small")
        FilledTextField(hint = "Fax Number :", "small")
        Spacer(modifier = Modifier.height(16.dp))
        CustomLabel(text = "Payment Method")
        PaymentMethod()
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp, color = colorResource(id = color.stroke))
        Spacer(modifier = Modifier.height(16.dp))
        Titles(text = "Order Totals")
        FinalAmountBox()
    }


    @Preview
    @Composable
    private fun CheckOutScreenPreview() {
        CheckOutScreen()
    }

    @Composable
    fun gradientColor(): Brush {
        return Brush.linearGradient(
            colors = listOf(
                colorResource(id = color.start_color),
                colorResource(id = color.middle_color),
                colorResource(id = color.end_color)
            )
        )
    }
    @Composable
    fun BodyContent(it: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = color.white)
                ),
                border = BorderStroke(0.5.dp, colorResource(id = color.stroke)),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .padding(it)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(16.dp)
            ) {
                BillingAddress(it)
            }
        }
    }

}
