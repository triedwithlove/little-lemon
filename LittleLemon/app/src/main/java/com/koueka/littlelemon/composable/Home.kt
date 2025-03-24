package com.koueka.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.koueka.littlelemon.R
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun Home(navController: NavHostController) {
    Column(modifier = Modifier.padding(top = 30.dp, start = 0.dp)) {
        Box(contentAlignment = Alignment.TopEnd,
            modifier = Modifier
            .fillMaxWidth()
//            .background(Color.Gray)
            .align(Alignment.End)) {
            ProfileButton() {
                navController.navigate(com.koueka.littlelemon.navigation.Profile.route)
            }
        }

        Text(
            text = "Home",
            modifier = Modifier
                .fillMaxWidth()
                .background(Green80)
                .padding(30.dp),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
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


/*
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home()
    }
}
*/