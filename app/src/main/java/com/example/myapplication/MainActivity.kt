package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("input") { InputScreen(navController) }
        composable("summary/{inputText}") { backStackEntry ->
            val inputText = backStackEntry.arguments?.getString("inputText") ?: ""
            SummaryScreen(inputText)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Ekran Glowny") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navController.navigate("input") }) {
                Text("Przejdz dalej")
            }
        }
    }
}
@Composable
fun InputScreen(navController: NavHostController) {
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("wpisz cos") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Wpisz coś") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("summary/$text") }) {
                Text("Przejdź dalej")
            }
        }
    }
}
@Composable
fun SummaryScreen(inputText: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("end screen") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("Wpisane dane: $inputText")

        }
    }
}
