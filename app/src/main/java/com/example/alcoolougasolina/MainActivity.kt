package com.example.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcoolOuGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var valorGasolina by remember { mutableStateOf("") }
    var valorAlcool by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var postoNome by remember { mutableStateOf("") }

    Column(
        Modifier
            .background(Color(0XFF00BCD4))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Alcool ou Gasolina",
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
            val ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
            val alcoolOuGasolina = if (ehGasolina) {
                "Gasolina"
            } else {
                "Alcool"
            }
            val cor = if (ehGasolina) {
                Color.Red
            } else {
                Color.Green
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = alcoolOuGasolina,
                style = TextStyle(
                    color = cor,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        TextField(
            value = valorGasolina,
            onValueChange = { valorGasolina = it },
            label = { Text(text = "Gasolina") }
        )

        Spacer(modifier = Modifier.size(16.dp))

        TextField(
            value = valorAlcool,
            onValueChange = { valorAlcool = it },
            label = { Text(text = "Alcool") }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = { showDialog = true }) {
            Text("Adicionar Posto")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Adicionar Posto")
                },
                text = {
                    Column {
                        TextField(
                            value = postoNome,
                            onValueChange = { postoNome = it },
                            label = { Text("Nome do Posto") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        // Lógica para salvar ou processar o nome do posto
                    }) {
                        Text("Salvar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    AlcoolOuGasolinaTheme {
        App()
    }
}