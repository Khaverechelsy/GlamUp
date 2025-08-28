package com.example.glamup.screens.appointment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentScreen(nav: NavHostController) {
    val db = remember { FirebaseFirestore.getInstance() }
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()   // ✅ for launching snackbars

    var service by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Book Appointment") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = service,
                onValueChange = { service = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Person icon"
                    )
                },
                label = { Text("Service (Haircut, Nails…)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Date icon"
                    )
                },
                label = { Text("Date (dd/mm/yyyy)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Time icon"
                    )
                },
                label = { Text("Time (HH:mm)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )




            Spacer(Modifier.height(16.dp))

            if (loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        if (service.isBlank() || date.isBlank() || time.isBlank()) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Please fill all fields")
                            }
                            return@Button
                        }
                        loading = true
                        val appt = hashMapOf(
                            "service" to service,
                            "date" to date,
                            "time" to time,
                            "userId" to userId
                        )
                        db.collection("appointments").add(appt).addOnCompleteListener { task ->
                            loading = false
                            if (task.isSuccessful) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Appointment received ✔")
                                }
                                nav.navigate("appointments")
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        task.exception?.localizedMessage ?: "Failed to book"
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Confirm Booking")
                }
            }
        }
    }
}
