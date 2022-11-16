package com.daviiid99.ahorcado_game

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
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
        val endImage = R.drawable.sad
        var congratsImage = R.drawable.congrats


        // Creamos objetos por cada widget
        var aceptarButton = findViewById<Button>(R.id.button)
        var salirButton = findViewById<Button>(R.id.reboot)
        var previewImage = findViewById<ImageView>(R.id.imageView)
        var currentCharTextView = findViewById<TextView>(R.id.currentCharTextView)
        var userInputEditText = findViewById<EditText>(R.id.editText)

        salirButton.setVisibility(View.GONE)

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

        salirButton.setOnClickListener{
            // Recargar la actividad
            finish();
            startActivity(getIntent());
        }

        aceptarButton.setOnClickListener {
            if (userInputEditText.text.length > 0){
                juego.setCurrentSpoiler(latestWord)
                 word = juego.comprobarLetra(userInputEditText.text.toString().uppercase())
                var bool = juego.getAllowedCharState()

                if (bool){
                    latestWord = word
                    currentCharTextView.text = latestWord
                    if (!latestWord.contains("_")){
                        previewImage.setImageResource(listaImagenes[indicePalabra]);
                        aceptarButton.setVisibility(View.GONE);

                        currentCharTextView.text = "¡Enhorabuena!";
                        salirButton.text = "Reiniciar";
                        userInputEditText.setVisibility(View.GONE);
                        salirButton.setVisibility(View.VISIBLE)
                    }
                } else {
                    previewImage.setImageResource(listaFails[failsCounter])
                    failsCounter ++

                    if (failsCounter == 10){
                        previewImage.setImageResource(endImage); // Show end image
                        currentCharTextView.text = "HAS PERDIDO\n¿Me das otra oportunidad?";
                        aceptarButton.setVisibility(View.GONE)
                        salirButton.text = "Reintentar";
                        userInputEditText.setVisibility(View.GONE);
                        salirButton.setVisibility(View.VISIBLE)
                    }
                }

            }


        }

    }
}