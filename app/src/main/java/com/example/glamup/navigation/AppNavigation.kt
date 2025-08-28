package com.example.glamup.navigation



import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glamup.screens.appointment.AppointmentsScreen
import com.example.glamup.screens.appointment.BookAppointmentScreen
import com.example.glamup.screens.home.HomeScreen
import com.example.glamup.screens.login.LoginScreen
import com.example.glamup.screens.register.RegisterScreen

@Composable
fun GlamUpApp() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(navController = nav, startDestination = ROUTE_REGISTER) {
            composable(ROUTE_REGISTER) { RegisterScreen(nav) }
            composable(ROUTE_LOGIN) { LoginScreen(nav) }
            composable(ROUTE_HOME) { HomeScreen(nav) }
            composable(ROUTE_BOOK) { BookAppointmentScreen(nav) }
            composable(ROUTE_APPOINTMENT) { AppointmentsScreen(nav) }
        }
    }
}