package com.koueka.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koueka.littlelemon.R
import com.koueka.littlelemon.navigation.clearSharedPreferences
import com.koueka.littlelemon.navigation.readAllDataSharedPreferences
import com.koueka.littlelemon.ui.theme.Green80
import com.koueka.littlelemon.ui.theme.LittleLemonTheme
import com.koueka.littlelemon.ui.theme.Yellow80


data class UserProfile(
    val fName: String,
    val lName: String,
    val email: String
)

@Composable
fun Profile(navController: NavHostController) {
    Column(modifier = Modifier.padding(top = 30.dp, start = 0.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Profile",
            modifier = Modifier
                .fillMaxWidth()
                .background(Green80)
                .padding(30.dp),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Profile information",
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
//            var firstName by remember { mutableStateOf("") }
//            var lastName by remember { mutableStateOf("") }
//            var email by remember { mutableStateOf("") }




//            val context = LocalContext.current
            val context = LocalContext.current
            fun logOut() {
                clearSharedPreferences(context)
                navController.navigate(com.koueka.littlelemon.navigation.Onboarding.route)
            }

            val userProfile: UserProfile = readAllDataSharedPreferences(context)
            android.util.Log.d("MAINACTIVITY", "${userProfile}")
/*
            fun checkFieldsAndRegister() {
                if ((firstName.isBlank())
                    || (lastName.isBlank())
                    || (email.isBlank())
                ) {
                    android.util.Log.d("MAINACTIVITY", "Empty string")
                    Toast.makeText(context,
                        "Registration unsuccessfull\nPlease enter all data", Toast.LENGTH_LONG).show()
                } else {
                    android.util.Log.d("MAINACTIVITY", "All strings filled")
                    //registration
                    saveSharedPreferences(context, firstName, lastName, email)
                    Toast.makeText(context,
                        "Registration successfull", Toast.LENGTH_LONG).show()
//                    navController.navigate(com.koueka.littlelemon.navigation.Home.route)

                }
            }
*/
            ProfileItem(label = "First Name", name = "${userProfile.fName}")
            ProfileItem(label = "Last Name", name = "${userProfile.lName}")
            ProfileItem(label = "Email", name = "${userProfile.email}")
            Box (
                modifier = Modifier
                    .fillMaxSize()
//                    .background(Color.White)
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { logOut() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Yellow80)

                ) {
                    Text(
                        text = "Log out",
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

@Composable
fun ProfileItem(label: String, name: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
//        .background(Color.White)
        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp)
        .border(1.dp, Color.Black),

    ) {
        Text(
            text = "$label:",
            modifier = Modifier
                .fillMaxWidth(0.3f)
//                .background(Color.Green)
                .padding(top = 10.dp, bottom = 10.dp, start = 5.dp),
            fontSize = 15.sp,
//            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "$name",
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color.Blue)
                .padding(top = 10.dp, bottom = 10.dp, start = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile()
    }
}
*/