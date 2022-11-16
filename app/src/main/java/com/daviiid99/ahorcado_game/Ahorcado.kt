package com.daviiid99.ahorcado_game

import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class Ahorcado() {
    private val palabras = listOf("CONEJO", "PERRO", "FRESA", "NOCHE", "LLUVIA", "VIENTO", "MIEDO", "FELIZ", "KOTLIN", "JAVA", "POKEMON", "IPHONE", "ANDROID" )
    var palabraActual = ""
    private var splitPalabraActual = mutableListOf<Char>()
    private var palabraACtualHolders = ""
    private var palabraActualSize = 0
    private var palabraActualIndex = 0
    private var allowedChar = 0
    private var allowed = false
    private var allowedCounter = 0

    fun devolverIndice () : Int{
        return palabraActualIndex
    }

    fun descomponerPalabra() {
        // Descomponemos la palabra en carácteres mas pequeños
        // Así será más sencillo comprobar si cada caracter que metan por teclado está

        palabraACtualHolders = "" // Resetea al generar nueva palabra
        palabraActualSize = 0
        splitPalabraActual =  mutableListOf<Char>()

        for ((index, value) in palabraActual.withIndex()) {
            palabraActualSize++
            splitPalabraActual.add(value)
            palabraACtualHolders = palabraACtualHolders.plus("_") // Huecos
        }
    }

    fun proveeCharAleatorios () : String {
        // Vamos a proveer de caracteres aleatorios de la palabra generada
        // Así el usuario tendrá alguna pista :)

         var  char1 = "1"
         var  char2 = "2" // Spoiler 2
         var  char3 = "3" // Spoiler 3

        var spoilers = 3
        var finalSpoiler = ""

        var lista = palabraACtualHolders.toCharArray()

        while (spoilers >= 0){
            var indice = Random.nextInt(palabraActualSize)
            var value = splitPalabraActual[indice]

            if (char1 != char2 && char1 != char3 && char2 != char3){
                if (char1.equals("1")){
                    lista[indice] = value
                }
                else if (char2.equals("2")){
                    lista[indice] = value
                }

                else if (char3.equals("3")){
                    lista[indice] = value
                }

                spoilers -=1
            }
        }


        for (char in lista){
            finalSpoiler = finalSpoiler.plus(char)
        }

        return finalSpoiler

    }

    fun incluirNuevaLetra(letra : String) : String{

        var lista = palabraACtualHolders.toCharArray()


        for ((index, value) in splitPalabraActual.withIndex()){
            if (splitPalabraActual[index] == letra[0]){
                lista[index] = letra[0]
            }

        }
        var finalSpoiler = ""

        for (char in lista){
            finalSpoiler = finalSpoiler.plus(char)
        }
        return finalSpoiler
    }


    fun palabraRandom() : String {
        // Vamos a recoger un elemento aleatorio de nuesta lista de palabras
        // Se asignara a una variable y se devolverá

        var indice = Random.nextInt(0, 13)
        var palabra = palabras[indice]
        palabraActual = palabra
        palabraActualIndex = indice
        descomponerPalabra()
        var finalSpoiler = proveeCharAleatorios()
        palabraACtualHolders = finalSpoiler
        return finalSpoiler
    }

    fun getAllowedCharState() : Boolean {
        return allowed

    }

    fun setCurrentSpoiler(palabra : String){
        palabraACtualHolders = palabra
    }

    fun comprobarLetra(letra : String) : String{
        allowedCounter = 0

        for ((index, value) in splitPalabraActual.withIndex()){
            if (letra == value.toString()){
                allowedChar = index
                allowedCounter ++
                allowed = true
            }  else {
                if (allowedCounter == 0){
                    allowed = false
                }
            }
        }

        var palabra = incluirNuevaLetra(letra)

        return palabra

    }
}