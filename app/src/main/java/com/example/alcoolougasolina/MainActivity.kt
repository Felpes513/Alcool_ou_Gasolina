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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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

    var valorGasolina by remember {
        mutableStateOf("")
    }
    var valorAlcool by remember {
        mutableStateOf("")
    }
    //Como centralizar o texto ou modificar o tema?
    Column(
        Modifier
            .background(Color(0XFF00BCD4))
            .fillMaxSize(),
        //Como colocar as informações no centro?
        verticalArrangement = Arrangement.Center,
        //E como alinhar?
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //como fazer para adicionar um texto?
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

        Spacer(modifier = Modifier.size(16.dp))//cria um espaço entre as informações

        /*Existem maneira diferentes de layout como Bpx que vai fazer tudo ficar um em cima do outro
        Existe também o colum que coloca cada uma em uma coluna, e também tem o row que deixa todos na mesma linha*/

        //Neste projeto terá um grupo de texto, como faz pra adicionar um grupo de texto?
        TextField(value = valorGasolina,
            onValueChange = {
                valorGasolina = it
            },
            label = {
                Text(text = "Gasolina")
            }
        )

        Spacer(modifier = Modifier.size(16.dp))//cria um espaço entre as informações

        TextField(
            value = valorAlcool,
            onValueChange = {
                valorAlcool = it
            },
            label = {
                Text(text = "Alcool")
            }
        )

    }

}

@Preview
@Composable
fun AppPreview() {
    AlcoolOuGasolinaTheme {
        App()
    }
}