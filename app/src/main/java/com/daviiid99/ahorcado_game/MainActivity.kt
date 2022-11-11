package com.daviiid99.ahorcado_game

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Contadores
        var failsCounter = 0
        var juego = Ahorcado()
        var indicePalabra = 0
        var miPalabra = ""
        var word = ""
        var latestWord = ""

        // Lista de imagenes
        val listaImagenes = listOf<Int>(R.drawable.conejo, R.drawable.perro, R.drawable.fresa, R.drawable.noche, R.drawable.lluvia, R.drawable.viento, R.drawable.miedo, R.drawable.feliz, R.drawable.kotlin, R.drawable.java, R.drawable.pokemon, R.drawable.iphone, R.drawable.android)
        val listaFails = listOf<Int>(R.drawable.fail1, R.drawable.fail2, R.drawable.fail3, R.drawable.fail4, R.drawable.fail5, R.drawable.fail6, R.drawable.fail7, R.drawable.fail8, R.drawable.fail9, R.drawable.fail10)

        // Creamos objetos por cada widget
        var spoilerTextView = findViewById<TextView>(R.id.spoilerTextView)
        var aceptarButton = findViewById<Button>(R.id.button)
        var previewImage = findViewById<ImageView>(R.id.imageView)
        var currentCharTextView = findViewById<TextView>(R.id.currentCharTextView)
        var userInputEditText = findViewById<EditText>(R.id.editText)

        fun crearPartida(){
            // Creamos una variable de la clase ahorcado
            juego = Ahorcado() // La usaremos para empezar una partida
            var empty = ""

            miPalabra = juego.palabraRandom()
            indicePalabra = juego.devolverIndice()
            //previewImage.setImageResource(listaImagenes[indicePalabra])
            currentCharTextView.text = miPalabra
            latestWord = miPalabra
        }

        // Empezar partida
        crearPartida()

        // Update
        userInputEditText.setOnClickListener{
            userInputEditText.setText("")
        }

        aceptarButton.setOnClickListener {
            if (userInputEditText.text.length > 0){
                juego.setCurrentSpoiler(latestWord)
                 word = juego.comprobarLetra(userInputEditText.text.toString())
                Toast.makeText(this, userInputEditText.text.toString(), Toast.LENGTH_SHORT).show()
                var bool = juego.getAllowedCharState()

                if (bool){
                    latestWord = word
                    currentCharTextView.text = latestWord
                    if (!latestWord.contains("_")){
                        previewImage.setImageResource(listaImagenes[indicePalabra])
                    }
                } else {
                    previewImage.setImageResource(listaFails[failsCounter])
                    failsCounter ++
                    Toast.makeText(this, miPalabra + "|" + word , Toast.LENGTH_SHORT).show()
                }

            }


        }

    }
}