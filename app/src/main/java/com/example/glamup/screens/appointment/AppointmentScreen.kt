package com.example.glamup.screens.appointment

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppointmentsScreen(nav: NavHostController) {
    val db = remember { FirebaseFirestore.getInstance() }
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val snackbar = remember { SnackbarHostState() }
    val coroutineScope = remember { CoroutineScope(Dispatchers.Main) }

    var appointments by remember { mutableStateOf(listOf<Appointment>()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        db.collection("appointments").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { snap ->
                appointments = snap.documents.mapNotNull { doc ->
                    Appointment(
                        id = doc.id,
                        service = doc.getString("service") ?: "",
                        date = doc.getString("date") ?: "",
                        time = doc.getString("time") ?: "",
                        userId = doc.getString("userId") ?: ""
                    )
                }
                loading = false
            }
            .addOnFailureListener { e ->
                error = e.localizedMessage ?: "Failed to load"
                loading = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            loading -> { CircularProgressIndicator() }
            error.isNotEmpty() -> { Text(error, color = Color.Red) }
            appointments.isEmpty() -> { Text("No appointments yet", color = Color.Gray) }
            else -> {
                appointments.forEach { appt ->
                    AppointmentCard(
                        appointment = appt,
                        onUpdated = { updated ->
                            db.collection("appointments").document(appt.id)
                                .update(
                                    mapOf(
                                        "service" to updated.service,
                                        "date" to updated.date,
                                        "time" to updated.time
                                    )
                                )
                                .addOnSuccessListener {
                                    appointments = appointments.map {
                                        if (it.id == appt.id) updated.copy(id = appt.id) else it
                                    }
                                    coroutineScope.launch {
                                        snackbar.showSnackbar("Updated ✔")
                                    }
                                }
                                .addOnFailureListener { err ->
                                    coroutineScope.launch {
                                        snackbar.showSnackbar(err.localizedMessage ?: "Update failed")
                                    }
                                }
                        },
                        onDeleted = {
                            db.collection("appointments").document(appt.id).delete()
                                .addOnSuccessListener {
                                    appointments = appointments.filterNot { it.id == appt.id }
                                    coroutineScope.launch {
                                        snackbar.showSnackbar("Deleted ✔")
                                    }
                                }
                                .addOnFailureListener { err ->
                                    coroutineScope.launch {
                                        snackbar.showSnackbar(err.localizedMessage ?: "Delete failed")
                                    }
                                }
                        },
                        onCall = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:+254700000000")
                            }
                            nav.context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }


    SnackbarHost(
        hostState = snackbar,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onUpdated: (Appointment) -> Unit,
    onDeleted: () -> Unit,
    onCall: () -> Unit
) {
    var service by remember { mutableStateOf(appointment.service) }
    var date by remember { mutableStateOf(appointment.date) }
    var time by remember { mutableStateOf(appointment.time) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("Service")
            OutlinedTextField(
                value = service,
                onValueChange = { service = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(6.dp))
            Text("Date")
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(6.dp))
            Text("Time")
            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onCall, shape = RoundedCornerShape(12.dp)) {
                    Text("Call Salon")
                }

                Button(
                    onClick = { showUpdateDialog = true },
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Update") }

                Button(
                    onClick = { showDeleteDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Delete") }
            }
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this appointment?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleted()
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) { Text("Delete", color = Color.Black) }
            },
            dismissButton = {
                Button(
                    onClick = { showDeleteDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) { Text("Cancel", color = Color.Black) }
            }
        )
    }


    AnimatedVisibility(
        visible = showUpdateDialog,
        enter = fadeIn(animationSpec = tween(300)) + slideInVertically(animationSpec = tween(300)) { it / 2 },
        exit = fadeOut(animationSpec = tween(300)) + slideOutVertically(animationSpec = tween(300)) { it / 2 }
    ) {
        Dialog(onDismissRequest = { showUpdateDialog = false }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Update Appointment", color = Color.Black, fontSize = 18.sp)
                    Spacer(Modifier.height(10.dp))

                    OutlinedTextField(
                        value = service,
                        onValueChange = { service = it },
                        label = { Text("Service") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Date") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Time") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(12.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                onUpdated(appointment.copy(service = service, date = date, time = time))
                                showUpdateDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                        ) { Text("Save", color = Color.Black) }

                        Button(
                            onClick = { showUpdateDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                        ) { Text("Back", color = Color.Black) }
                    }
                }
            }
        }
    }
}