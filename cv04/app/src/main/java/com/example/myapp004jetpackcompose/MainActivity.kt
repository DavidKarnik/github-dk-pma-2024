package com.example.myapp004jetpackcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExample()
        }
    }
}


/*
Tato funkce definuje samotnou Composable, což je funkce
v Jetpack Compose, která vykresluje UI.
V tomto případě bude tato funkce obsahovat
veškerou logiku a UI pro tuto jednoduchou aplikaci.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeExample() {

    /*
    První řádek inicializuje stav
    pro první textové pole (zde pro jméno).
    V Compose je důležité mít stav pro každý vstup,
    aby se mohly UI prvky aktualizovat,
    když se změní vstup uživatele.

    remember znamená, že hodnotu tohoto stavu
    si Compose pamatuje mezi změnami zobrazení (recompositions).

    mutableStateOf("") nastavuje počáteční hodnotu
    jako prázdný textový řetězec.
    Kdykoliv se stav name změní, Compose znovu vykreslí části,
    které závisí na této hodnotě.

    */

    // Stavy pro jednotlivé textové vstupy
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var fingers by remember { mutableStateOf("") }

    // Přidáme Scaffold, abychom mohli přidat TopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Moje Aplikace",
                            color = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        }
    )
    { innerPadding ->
        // Zbytek obsahu se vykresluje uvnitř Scaffold s paddingem
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // padding kolem obsahu
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Textová pole pro vstupy
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Jméno") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Příjmení") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = age,
                onValueChange = {
                    // Omezíme vstup na číslice a kontrolujeme, že číslo není větší než 150
                    if (it.all { char -> char.isDigit() } && it.toIntOrNull()
                            ?.let { it <= 150 } == true) {
                        age = it
                    }
                },
                label = { Text("Věk (hodnota menší než 151)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = place,
                onValueChange = { place = it },
                label = { Text("Bydliště") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Oblíbená barva") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = fingers,
                onValueChange = {
                    if (it.all { char -> char.isDigit() } && it.toIntOrNull()
                            ?.let { it <= 10 } == true) {
                        fingers = it
                    }
                },
                label = { Text("Počet prstů na rukou (hodnota <= 10)") },
                modifier = Modifier.fillMaxWidth()
            )
            // Tlačítka Odeslat a Vymazat
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        resultText =
                            "Jmenuji se $name $surname. Je mi $age let a moje bydliště je $place. Moje oblíbená barva je $color a na rukou mám $fingers prstů."
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Odeslat")
                }

                Button(
                    onClick = {
                        name = ""
                        surname = ""
                        age = ""
                        place = ""
                        resultText = ""
                        color = ""
                        fingers = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF0000),  // Hexadecimální barva pro pozadí tlačítka
                        contentColor = Color.White  // Barva textu na tlačítku
                    )
                ) {
                    Text("Vymazat")
                }
            }


            // Výsledek
            if (resultText.isNotEmpty()) {
                Text(
                    text = resultText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExample()
}