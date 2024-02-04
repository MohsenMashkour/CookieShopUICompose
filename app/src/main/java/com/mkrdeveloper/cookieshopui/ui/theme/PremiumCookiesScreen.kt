package com.mkrdeveloper.cookieshopui.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkrdeveloper.cookieshopui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumCookiesScreenUi(modifier: Modifier = Modifier) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier
            .nestedScroll(
                scrollBehavior.nestedScrollConnection
            ),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavBar()
        },
        containerColor = Color.DarkGray,
        content = { contentPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(Color.DarkGray)
                    .verticalScroll(scrollState)
            ) {
                ScreenLabelSection()
                PremiumCookie()
                Spacer(modifier = Modifier.height(20.dp))
                OffersSection()
            }
        }
    )
}

@Composable
fun OffersSection() {
    OffersLabel()
    CookiesOffers()
}

@Composable
fun CookiesOffers() {
    val cookieOffersList = listOf(
        CookiesData(
            image = painterResource(id = R.drawable.cookie3),
            name = "Oatmeal \nCookie",
            normalPrice = "20 $",
            currentPrice = "14 $"
        ), CookiesData(
            image = painterResource(id = R.drawable.cookie7),
            name = "Peanut \nCookie",
            normalPrice = "21 $",
            currentPrice = "14 $"
        ), CookiesData(
            image = painterResource(id = R.drawable.cookie8),
            name = "Walnut \nCookie",
            normalPrice = "22 $",
            currentPrice = "15 $"
        ), CookiesData(
            image = painterResource(id = R.drawable.cookie9),
            name = "Raisins \nCookie",
            normalPrice = "18 $",
            currentPrice = "14 $"
        )
    )
    LazyRow(modifier = Modifier
        .fillMaxWidth(),
        content = {
            items(cookieOffersList.size) { it ->
                cookieOffersList[it].normalPrice?.let { normalPrice ->
                    OfferCardBuilder(
                        image = cookieOffersList[it].image,
                        name = cookieOffersList[it].name,
                        normalPrice = normalPrice,
                        currentPrice = cookieOffersList[it].currentPrice
                    )
                }
            }
        })
}

@Composable
fun OfferCardBuilder(
    image: Painter,
    name: String,
    normalPrice: String,
    currentPrice: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {

        Card(
            modifier
                .fillMaxWidth()
                .height(150.dp)
                .aspectRatio(2.5f),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF3D3434)
            ),
            shape = RoundedCornerShape(25.dp,25.dp,90.dp,25.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = image, contentDescription = name,
                    modifier.padding(start = 15.dp)
                        .clip(CircleShape)
                        .size(120.dp)
                        .shadow(elevation = 50.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name, color = Color.White,
                        fontWeight = FontWeight.ExtraBold, fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.sora_thin))
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.premium),
                            contentDescription = null,
                            tint = Color(0xffe38035),
                            modifier = Modifier.size(25.dp)
                        )
                        Text(text = "premium", color = Color(0xffe38035))
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = normalPrice,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.sora))
                    )
                    Text(
                        text = currentPrice,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.sora))
                    )
                }
            }

        }
        Image(
            painter = painterResource(id = R.drawable.arrow_forward_24),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 26.dp)
                .clip(CircleShape)
                .size(50.dp)
                .align(alignment = Alignment.BottomEnd)
                .background(Color.Black)

        )
    }
}

@Composable
fun OffersLabel() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Offers",
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.sora_thin))
        )
        Text(
            text = "see more",
            Modifier.padding(bottom = 6.dp),
            color = Color(0xffe38035)
        )
    }
}

@Composable
fun PremiumCookie() {
    val cookiesList = listOf(
        CookiesData(
            image = painterResource(id = R.drawable.cookie1),
            name = "Chocolate\nChips",
            currentPrice = "20n \$"
        ),
        CookiesData(
            image = painterResource(id = R.drawable.cookie2),
            name = "Double\nChocolate",
            currentPrice = "18 \$"
        ),
        CookiesData(
            image = painterResource(id = R.drawable.cookie4),
            name = "Sugar\nFree",
            currentPrice = "19 \$"
        ),
        CookiesData(
            image = painterResource(id = R.drawable.cookie5),
            name = "Diet\nCookie",
            currentPrice = "24 \$"
        ),
        CookiesData(
            image = painterResource(id = R.drawable.cookie6),
            name = "Low\nFat",
            currentPrice = "21 \$"
        )

    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp),
        content = {
            items(cookiesList.size) {
                CardBuilder(
                    image = cookiesList[it].image,
                    name = cookiesList[it].name,
                    price = cookiesList[it].currentPrice
                )
                Spacer(modifier = Modifier.width(28.dp))
            }
        })
}

@Composable
fun CardBuilder(image: Painter, name: String, price: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier
                .padding(top = 100.dp)
                .height(150.dp)
                .width(160.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF3D3434)
            ),
            shape = RoundedCornerShape(25.dp, 25.dp, 95.dp, 25.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = name, color = Color.White,
                    fontWeight = FontWeight.ExtraBold, fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.sora_thin))
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.premium),
                        contentDescription = null,
                        tint = Color(0xffe38035),
                        modifier = Modifier.size(25.dp)
                    )
                    Text(text = "premium", color = Color(0xffe38035))
                }
                Text(
                    text = price,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.sora))
                )

            }
        }
        Image(
            painter = image, contentDescription = name,
            modifier
                .clip(CircleShape)
                .size(120.dp)
                .shadow(elevation = 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow_forward_24), contentDescription = name,
            modifier
                .clip(CircleShape)
                .size(50.dp)
                .align(alignment = Alignment.BottomEnd)
                .background(Color.Black)
        )
    }
}

@Composable
fun ScreenLabelSection(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.premium), contentDescription = null,
                tint = Color(0xffe38035),
                modifier = modifier.size(35.dp)
            )
            Text(
                text = "Premium",
                color = Color(0xffe38035),
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.sora_thin))
            )
        }
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Cookies",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.sora_thin))
            )
            Text(text = "see more", color = Color(0xffe38035))
        }
    }
}

@Composable
fun NavBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier
            .padding(8.dp)
            .clip(
                RoundedCornerShape(
                    35.dp,
                    35.dp,
                    18.dp,
                    18.dp
                )
            ),
        containerColor = Color(0xFF292929),
        contentColor = Color(0xffe38035),
        content = {
            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "",
                    tint = Color(0xffe38035)
                )
            },
                label = {
                    Text(text = "Home", color = Color(0xffe38035))
                })
            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "",
                    tint = Color(0xffe38035)
                )
            },
                label = {
                    Text(text = "Cart", color = Color(0xffe38035))
                })
            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    tint = Color(0xffe38035)
                )
            },
                label = {
                    Text(text = "Search", color = Color(0xffe38035))
                })
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "MkrDeveloper",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cabin))
            )
        },
        modifier = modifier
            .wrapContentSize()
            .padding(6.dp),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "",
                modifier
                    .size(70.dp)
                    .padding(6.dp)
            )
        },
        actions = {
            Box(
                modifier = modifier.size(55.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shopping_bag_24),
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .size(45.dp)
                        .align(alignment = Alignment.Center)
                        .background(Color.Black)
                        .padding(5.dp)
                )
                Badge {
                    Text(
                        text = "8", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.sora))
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray,
            scrolledContainerColor = Color.DarkGray
        )
    )

}
