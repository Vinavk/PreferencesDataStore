package com.example.preferences_datastore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.preferences_datastore.themes.HomePage
import com.example.preferences_datastore.ui.theme.Preferences_DataStoreTheme
import com.example.preferences_datastore.viewmodel.DataViewModel
import com.example.preferences_datastore.viewmodel.DatastoreFactory

class MainActivity : ComponentActivity() {


    private val Context.dataStore by preferencesDataStore("datafilesss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel by viewModels<DataViewModel> {
            DatastoreFactory(dataStore)
        }


        setContent {
            Preferences_DataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navcontrol  = rememberNavController()

                   NavHost(navController = navcontrol, startDestination = "HomePage"){
                       composable("HomePage"){
                           HomePage(some = viewmodel,navcontrol)
                       }

                       composable("DataScreen"){
                           DataScreen(viewmodel)
                       }
                   }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Preferences_DataStoreTheme {
        Greeting("Android")
    }
}