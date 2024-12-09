package com.example.myapp010adatastore

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// Rozšíření pro DataStore
val AppCompatActivity.dataStore by preferencesDataStore(name = "MyAppPrefs")

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var adultCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializace UI komponent
        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        adultCheckBox = findViewById(R.id.adultCheckBox)
        saveButton = findViewById(R.id.saveButton)
        loadButton = findViewById(R.id.loadButton)

        // Uložení dat
        saveButton.setOnClickListener {
            saveData(
                name = nameEditText.text.toString(),
                age = ageEditText.text.toString().toIntOrNull() ?: 0,
                isAdult = adultCheckBox.isChecked
            )
        }

        // Načtení dat
        loadButton.setOnClickListener {
            val data = loadData()
            nameEditText.setText(data.name)
            ageEditText.setText(data.age.toString())
            adultCheckBox.isChecked = data.isAdult

            Toast.makeText(this, "Data načtena", Toast.LENGTH_SHORT).show()
        }
    }

    // Funkce pro uložení dat do DataStore
    private fun saveData(name: String, age: Int, isAdult: Boolean) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.NAME_KEY] = name
                preferences[PreferencesKeys.AGE_KEY] = age
                preferences[PreferencesKeys.IS_ADULT_KEY] = isAdult
            }
            Toast.makeText(this@MainActivity, "Data uložena", Toast.LENGTH_SHORT).show()
        }
    }

    // Funkce pro načtení dat z DataStore
    private fun loadData(): UserPreferences {
        return runBlocking {
            dataStore.data.map { preferences ->
                UserPreferences(
                    name = preferences[PreferencesKeys.NAME_KEY] ?: "",
                    age = preferences[PreferencesKeys.AGE_KEY] ?: 0,
                    isAdult = preferences[PreferencesKeys.IS_ADULT_KEY] ?: false
                )
            }.first()
        }
    }

    // Klíče pro DataStore
    private object PreferencesKeys {
        val NAME_KEY = stringPreferencesKey("name")
        val AGE_KEY = intPreferencesKey("age")
        val IS_ADULT_KEY = booleanPreferencesKey("isAdult")
    }

    // Datová třída pro načtení dat
    data class UserPreferences(val name: String, val age: Int, val isAdult: Boolean)
}
