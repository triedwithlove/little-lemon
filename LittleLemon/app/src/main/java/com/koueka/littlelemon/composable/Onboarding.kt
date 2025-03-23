package com.koueka.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koueka.littlelemon.R
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LittleLemonTheme
import com.koueka.littlelemon.ui.theme.Yellow80

@Composable
fun Onboarding() {
    Column(modifier = Modifier.padding(top = 0.dp, start = 0.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = "Let's get to know you",
            modifier = Modifier
                .fillMaxWidth()
                .background(Green80)
                .padding(30.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Personal information",
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color.White)
                .padding(top = 40.dp, bottom = 10.dp, start = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = firstName,
                onValueChange = {newValue -> firstName = newValue},
                label = { Text(text = "First Name")},
                //placeholder = { Text(text = "First Name")}
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = {newValue -> lastName = newValue},
                label = { Text(text = "Last Name")},
                //placeholder = { Text(text = "First Name")}
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {newValue -> email = newValue},
                label = { Text(text = "Email")},
                //placeholder = { Text(text = "First Name")}
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)
            )

            Box (
                modifier = Modifier
                    .fillMaxSize()
//                    .background(Color.White)
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Yellow80)

                ) {
                    Text(
                        text = "Register",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }


        }





    }

}

@Preview(showBackground = true)
@Composable
fun OnbordingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}

