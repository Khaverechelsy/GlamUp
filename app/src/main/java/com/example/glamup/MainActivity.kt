package com.example.glamup

//import  android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import com.example.glamup.navigation.GlamUpApp
//import com.google.firebase.FirebaseApp
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        FirebaseApp.initialize(this)
//
//        setContent {
//            GlamUpApp()
//
//        }
//    }
//}



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.glamup.navigation.GlamUpApp
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Initialize Firebase properly
        FirebaseApp.initializeApp(this)

        setContent {
            GlamUpApp()
        }
    }
}
