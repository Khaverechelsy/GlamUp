package com.example.glamup.screens.home

import androidx.compose.material.icons.automirrored.filled.List
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.glamup.navigation.ROUTE_APPOINTMENT
import com.example.glamup.navigation.ROUTE_BOOK

@Composable
fun HomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFE6E6), Color(0xFFFFB3B3))
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { nav.navigate(ROUTE_BOOK) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Icon(Icons.Filled.CalendarToday, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("Book Appointment", color = Color.White, fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))


        Button(
            onClick = { nav.navigate(ROUTE_APPOINTMENT) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C8C)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("View Appointments", color = Color.White, fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))


        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:+254719890401".toUri()
                }
                nav.context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4D4D)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Icon(Icons.Filled.Call, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("Call Salon", color = Color.White, fontSize = 18.sp)
        }
    }
}

