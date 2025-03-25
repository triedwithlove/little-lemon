package com.koueka.littlelemon.composable

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.koueka.littlelemon.R
import com.koueka.littlelemon.repository.data.MenuItemRoom
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LightColor1
import com.koueka.littlelemon.ui.theme.LittleLemonTheme
import com.koueka.littlelemon.ui.theme.Yellow80
import com.koueka.littlelemon.ui.theme.karlaFamily
import com.koueka.littlelemon.ui.theme.markazitextFamily


@Composable
fun Home(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>) {
    Column(modifier = Modifier.padding(top = 40.dp, start = 0.dp)) {

        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxWidth().height(80.dp).padding(top = 15.dp, bottom = 15.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.scale(0.6f).padding(0.dp)
            )
            Box(contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth().padding(end = 5.dp)
            ) {
                ProfileButton() {
                    navController.navigate(com.koueka.littlelemon.navigation.Profile.route)
                }
            }
        }

        //var list of category
        var categoryList: List<String> = databaseMenuItems.map { it.category }
        //remove duplicates
        categoryList = categoryList.toSet().toList()

        // add variable state to check if user selected a category
        var selectedMenuCategory by remember { mutableStateOf("Undefined") }

        // set menuItems variable here
        var menuItems = if (!(selectedMenuCategory.equals("Undefined"))
            && (categoryList.contains(selectedMenuCategory))) {
            databaseMenuItems.filter { menuIt ->
                menuIt.category.contains(selectedMenuCategory, ignoreCase = true)}
        } else {
            databaseMenuItems
        }

        //hero section
        var menuItemList = HeroComposable(menuItems = menuItems)

        Column (modifier = Modifier.fillMaxWidth().padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
            Text(
                text = "ORDER FOR DELIVERY!",
                fontSize = 20.sp,
                fontFamily = karlaFamily,
                fontWeight = FontWeight.Bold,
            )
            LazyRow(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                items(
                    items = categoryList,
                    itemContent = { category ->
                        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Button(
                                onClick = { selectedMenuCategory = category
                                    android.util.Log.d("MainActivity",
                                        "Home Composable cat= $selectedMenuCategory ")},
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(LightColor1)
                            ) {
                                Text(text = category,
                                    fontSize = 18.sp,
                                    fontFamily = karlaFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black)
                            }
                        }
                    }
                )
            }
        }

        //menu section
        MenuItems(categoryList, items = menuItemList)
    }
}

@Composable
fun ProfileButton(onClick: () -> Unit) {
    val image: Painter = painterResource(id = R.drawable.profile)
    Image(
        painter = image,
        contentDescription = "profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(50.dp).clickable { onClick() }
    )
}

@Composable
private fun HeroComposable(menuItems: List<MenuItemRoom>): List<MenuItemRoom> {

    var menuItemList = menuItems

    Column (modifier = Modifier
        .fillMaxWidth()
        .background(Green80)
        .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        var searchPhrase by remember { mutableStateOf("") }

        //restaurant name
        Text(
            text = "Little Lemon",
            fontSize = 60.sp,
            fontFamily = markazitextFamily,
            fontWeight = FontWeight.Medium,
            color = Yellow80,
            modifier = Modifier.padding(0.dp).height(45.dp)
        )
        //city and description
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxWidth(0.55f).padding(0.dp)
            ) {
                //city
                Text(
                    text = "Chicago",
                    fontSize = 40.sp,
                    fontFamily = markazitextFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    modifier = Modifier.height(37.dp)
                )
                //Description
                Text(
                    text = "We are a family-owned mediterranean restaurant, focused on" +
                            " traditional recipes served with a modern twist",
                    color = Color.White,
                    fontFamily = karlaFamily,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                )
            }
            //image
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero section image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp).clip(RoundedCornerShape(10.dp))
            )

        }
        //search
        TextField(
            value = searchPhrase,
            onValueChange = {newValue -> searchPhrase = newValue},
            placeholder = { Text(text = "Enter search phrase")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth()
                .padding(top = 15.dp, bottom = 5.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        if (searchPhrase.isBlank()) {
            //No Operation
        } else {
            menuItemList = menuItemList.filter {
                    menuItemRoom ->  menuItemRoom.title.contains(searchPhrase, ignoreCase = true)}
        }

    }
    return menuItemList
}


@Composable
private fun OrderForDelivery(categories: List<String>) {
    Column (modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 20.sp,
            fontFamily = karlaFamily,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            items(
                items = categories,
                itemContent = { category ->
                    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                        Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(LightColor1)
                        ) {
                            Text(text = category,
                                fontSize = 18.sp,
                                fontFamily = karlaFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black)
                        }
                    }
                }
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
private fun MenuItems(categoryList: List<String>, items: List<MenuItemRoom>) {

    Column(
        modifier = Modifier.fillMaxHeight().fillMaxHeight().padding(bottom = 40.dp)) {

        LazyColumn(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp, end = 10.dp)) {
/*
            item{
                OrderForDelivery(categories = categoryList)
            }
*/
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
                                modifier = Modifier.fillMaxWidth(0.75f).padding(end = 5.dp)
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
                                    modifier = Modifier.padding(5.dp),
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