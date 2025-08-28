package com.example.glamup.screens.appointment

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Appointment(
    val id: String = "",
    val service: String = "",
    val date: String = "",
    val time: String = "",
    val userId: String = ""
)

private fun QueryDocumentSnapshot.toAppointment(): Appointment = Appointment(
    id = id,
    service = getString("service") ?: "",
    date = getString("date") ?: "",
    time = getString("time") ?: "",
    userId = getString("userId") ?: ""
)
