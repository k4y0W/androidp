package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "accelerometer") {
                composable("accelerometer") {
                    val viewModel: SensorViewModel = viewModel()
                    SensorScreen(
                        title = "Accelerometer",
                        sensorType = Sensor.TYPE_ACCELEROMETER,
                        sensorManager = sensorManager,
                        viewModel = viewModel,
                        onNavigate = { navController.navigate("gyroscope") }
                    )
                }
                composable("gyroscope") {
                    val viewModel: SensorViewModel = viewModel()
                    SensorScreen(
                        title = "Gyroscope",
                        sensorType = Sensor.TYPE_GYROSCOPE,
                        sensorManager = sensorManager,
                        viewModel = viewModel,
                        onNavigate = { navController.navigate("magnetometer") }
                    )
                }
                composable("magnetometer") {
                    val viewModel: SensorViewModel = viewModel()
                    SensorScreen(
                        title = "Magnetometer",
                        sensorType = Sensor.TYPE_MAGNETIC_FIELD,
                        sensorManager = sensorManager,
                        viewModel = viewModel,
                        onNavigate = { navController.navigate("accelerometer") }
                    )
                }
            }
        }
    }
}
