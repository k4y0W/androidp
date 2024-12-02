package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class SensorViewModel : ViewModel() {
    private val _sensorData = mutableStateOf(listOf(0f, 0f, 0f))
    val sensorData: State<List<Float>> = _sensorData

    fun updateSensorData(x: Float, y: Float, z: Float) {
        _sensorData.value = listOf(x, y, z)
    }
}
