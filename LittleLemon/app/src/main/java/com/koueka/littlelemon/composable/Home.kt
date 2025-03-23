package com.koueka.littlelemon.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun Home() {
    Column(modifier = Modifier.padding(top = 30.dp, start = 0.dp)) {
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


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home()
    }
}
