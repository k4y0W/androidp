package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SensorScreen(
    title: String,
    sensorType: Int,
    sensorManager: SensorManager,
    viewModel: SensorViewModel = viewModel(),
    onNavigate: () -> Unit
) {
    val sensorData by viewModel.sensorData

    // Rejestracja sensora
    DisposableEffect(sensorType) {
        val sensor = sensorManager.getDefaultSensor(sensorType)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    viewModel.updateSensorData(it.values[0], it.values[1], it.values[2])
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    // Interfejs użytkownika
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "X: ${sensorData[0]}")
        Text(text = "Y: ${sensorData[1]}")
        Text(text = "Z: ${sensorData[2]}")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigate) {
            Text("Next Sensor")
        }
    }
}
