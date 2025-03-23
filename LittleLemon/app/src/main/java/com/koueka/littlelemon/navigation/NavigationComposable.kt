package com.koueka.littlelemon.navigation

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

import com.koueka.littlelemon.composable.Home
import com.koueka.littlelemon.composable.Profile
import com.koueka.littlelemon.composable.Onboarding

const val SHARED_PREFERENCES_KEY = "LilLemPrefs"
const val SHARED_PREFERENCES_DEFAULT_VALUE = "Empty"
const val SHARED_PREFERENCES_FIRST_NAME = "FIRST_NAME"
const val SHARED_PREFERENCES_LAST_NAME = "LAST_NAME"
const val SHARED_PREFERENCES_EMAIL = "EMAIL"



@Composable
fun LLNavigation(naviController: NavHostController) {

    //set defqult route to Home assuming that sharedpreferences have been set
    var destination = Home.route
    //check sharedPreference
    if (readSharedPreferences(naviController.context)) {
        destination = Onboarding.route
    }

    NavHost(navController = naviController, startDestination = destination) {
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            Profile()
        }
        composable(Onboarding.route) {
            Onboarding(naviController)
        }
    }
}

//if the sharedPreferences have not been set the function will return true
// and else otherwise
fun readSharedPreferences(context: Context): Boolean {
    val sharedPreferences : SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(
        SHARED_PREFERENCES_FIRST_NAME,
        SHARED_PREFERENCES_DEFAULT_VALUE)

    val result = firstName.equals(SHARED_PREFERENCES_DEFAULT_VALUE)
    return result
}

//if the sharedPreferences have not been set the function will return true
// and else otherwise
fun saveSharedPreferences(
    context: Context,
    firstName: String,
    lastName: String,
    email: String
) {
    val sharedPreferences : SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    //save data
    sharedPreferences.edit()
        .putString(SHARED_PREFERENCES_FIRST_NAME, firstName)
        .putString(SHARED_PREFERENCES_LAST_NAME, lastName)
        .putString(SHARED_PREFERENCES_DEFAULT_VALUE, email)
        .commit()
}
