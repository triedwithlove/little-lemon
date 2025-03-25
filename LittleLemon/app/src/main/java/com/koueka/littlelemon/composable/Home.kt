package com.koueka.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.koueka.littlelemon.R
import com.koueka.littlelemon.repository.data.MenuItemRoom
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LittleLemonTheme
import com.koueka.littlelemon.ui.theme.Yellow80
import com.koueka.littlelemon.ui.theme.karlaFamily
import com.koueka.littlelemon.ui.theme.markazitextFamily


@Composable
fun Home(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>) {
    Column(modifier = Modifier.padding(top = 40.dp, start = 0.dp)) {

        var menuItemList = databaseMenuItems

        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 15.dp, bottom = 15.dp)
//            .background(Color.Blue)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .scale(0.6f)
                    .padding(0.dp)
//                    .background(Color.Red)
            )
            Box(contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp)
//            .background(Color.Gray)
//                    .align(Alignment.End)
            ) {
                ProfileButton() {
                    navController.navigate(com.koueka.littlelemon.navigation.Profile.route)
                }
            }
        }
        //hero section
        Column (modifier = Modifier
            .fillMaxWidth()
            .background(Green80)
            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
            var search by remember { mutableStateOf("") }
            //restaurant name
            Text(
                text = "Little Lemon",
                fontSize = 60.sp,
                fontFamily = markazitextFamily,
                fontWeight = FontWeight.Medium,
                color = Yellow80,
                modifier = Modifier
//                    .background(Color.Magenta)
                    .padding(0.dp)
                    .height(45.dp)
            )
            //city and description
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(Color.Red)
            ) {
                Column (verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .padding(0.dp)
                ) {
                   //city
                    Text(
                        text = "Chicago",
                        fontSize = 40.sp,
                        fontFamily = markazitextFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
//                            .background(Color.Black)
                            .height(37.dp)
                    )
                    //Description
                    Text(
                        text = "We are a family-owned mediterranean restaurant, focused on" +
                                " traditional recipes served with a modern twist",
                        color = Color.White,
                        fontFamily = karlaFamily,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 10.dp)
//                            .background(Color.Black)
                    )
                }
                //image
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero section image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
//                    .background(Color.Red)
                )

            }
            //search
            TextField(
                value = search,
                onValueChange = {newValue -> search = newValue},
                label = { Text(text = "search")},
                //placeholder = { Text(text = "First Name")}
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
                    //.background(Color.White)
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        //menu section
        MenuItems(items = menuItemList)

    }

}

@Composable
fun ProfileButton(onClick: () -> Unit) {
    val image: Painter = painterResource(id = R.drawable.profile)
    Image(
        painter = image,
        contentDescription = "profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clickable { onClick() }
    )
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Column {
                    HorizontalDivider(thickness = 2.dp,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp) )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Column (
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(end = 5.dp)
                        ) {
                            Text(
                                fontFamily = karlaFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                text = menuItem.title,
                            )
                            Text(
                                fontFamily = karlaFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                minLines = 1,
                                lineHeight = 15.sp,
                                text = menuItem.description,
                            )
                            Text(
                                fontFamily = karlaFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    // .weight(1f)
                                    .padding(5.dp),
                                //     textAlign = TextAlign.Right,
                                //text = "$" + menuItem.price
                                text = "$%.2f".format(menuItem.price)
                            )
                        }
                        //image to display with glide
                        GlideImage(model = menuItem.image,
                            contentDescription = "${menuItem.title} image")
                    }
                }
            }
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home()
    }
}
*/