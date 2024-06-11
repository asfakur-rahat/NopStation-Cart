package com.test.nopstation_cart.screens.orderlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.nopstation_cart.R
import com.test.nopstation_cart.R.*
import com.test.nopstation_cart.db.dbmodel.OrderEntity
import com.test.nopstation_cart.ui.custom.CustomBadge
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class OrderListFragment : Fragment(layout.fragment_order_list) {

    private val viewmodel: OrderListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getOrders()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getOrders()
        viewmodel.updateCartItemCount()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OrderListPage()
            }
        }
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
    fun OrderListPage() {
        val cartItemCount by viewmodel.cartItemCount.observeAsState(0)
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(
                        brush = gradientColor()
                    ),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = colorResource(id = color.white)
                    ),
                    title = {
                        Text(text = "Placed Order List")
                    }
                )
            }
        ) {
            BodyContent(it)
        }
    }

    @Composable
    fun BodyContent(it: PaddingValues) {
        val message by viewmodel.orders.observeAsState()
        if (message.isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(id = color.middle_color)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        start = it.calculateStartPadding(LayoutDirection.Ltr),
                        end = it.calculateStartPadding(LayoutDirection.Ltr)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(message!!) { order ->
                    OrderCard(order = order)
                }
            }
        }
    }

    @Composable
    fun OrderCard(order: OrderEntity) {
        OutlinedCard(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = "Order Details", modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(
                            id = color.order_tag
                        )
                    )
                    .padding(start = 12.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                fontSize = 18.sp
            )
            TextField(
                label = "Order Number :",
                text = "${order.id}",
                labelColor = colorResource(id = color.black),
                textColor = colorResource(id = color.black)
            )
            TextField(
                label = "Order Status :",
                text = order.status,
                labelColor = colorResource(
                    id = color.category_color
                ),
                textColor = colorResource(id = color.category_color)
            )
            TextField(
                label = "Order Date :",
                text = order.date,
                labelColor = colorResource(id = color.black),
                textColor = colorResource(
                    id = color.black
                )
            )
            TextField(
                label = "Order Total :",
                text = order.totalPrice,
                labelColor = colorResource(id = color.product_price),
                textColor = colorResource(id = color.product_price)
            )
        }
    }

    @Composable
    fun TextField(label: String, text: String, labelColor: Color, textColor: Color) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, fontWeight = FontWeight.Bold, color = labelColor, fontSize = 19.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                color = textColor,
                fontSize = 17.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Preview
    @Composable
    private fun OrderListPreview() {
        Surface {
            OrderCard(
                order = OrderEntity(
                    id = 1,
                    token = "asdasguagduas",
                    email = "2021-03-02",
                    orderId = "ashdgasydaudasdaaadadasadaydgasu",
                    date = "2021-03-02",
                    status = "Complete",
                    item = emptyList(),
                    totalPrice = "$10000"
                )
            )
        }
    }
}
