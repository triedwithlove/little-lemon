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
import com.koueka.littlelemon.composable.UserProfile


const val SHARED_PREFERENCES_KEY = "LilLemPrefs"
const val SHARED_PREFERENCES_DEFAULT_VALUE = "Empty"
const val SHARED_PREFERENCES_FIRST_NAME = "FIRST_NAME"
const val SHARED_PREFERENCES_LAST_NAME = "LAST_NAME"
const val SHARED_PREFERENCES_EMAIL = "EMAIL"



@Composable
fun LLNavigation(naviController: NavHostController) {

    //set default route to Home assuming that sharedPreferences have been set
    var destination = Home.route
/* just for now
    //check sharedPreference
    if (readSharedPreferences(naviController.context)) {
        destination = Onboarding.route
    }
*/
    NavHost(navController = naviController, startDestination = destination) {
        composable(Home.route) {
            Home(naviController)
        }
        composable(Profile.route) {
            Profile(naviController)
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

fun readAllDataSharedPreferences(context: Context): UserProfile {
    val sharedPreferences : SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(
        SHARED_PREFERENCES_FIRST_NAME,
        SHARED_PREFERENCES_DEFAULT_VALUE)

    val lastName = sharedPreferences.getString(
        SHARED_PREFERENCES_LAST_NAME,
        SHARED_PREFERENCES_DEFAULT_VALUE)

    val email = sharedPreferences.getString(
        SHARED_PREFERENCES_EMAIL,
        SHARED_PREFERENCES_DEFAULT_VALUE)

    return UserProfile(firstName!!, lastName!!, email!!)
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
        .putString(SHARED_PREFERENCES_EMAIL, email)
        .commit()
}

fun clearSharedPreferences(context: Context) {
    val sharedPreferences : SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    //save data
    sharedPreferences.edit().clear().commit()
}
