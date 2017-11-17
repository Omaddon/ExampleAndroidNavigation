package com.ammyt.examplesmasterclass.example_01

import java.io.Serializable

// Esto es un SINGLETONE
object Model_mago {

    private var mago: String = "Carolinus"

    fun getMago() = mago
}


// Con data class nos genera los getter y setter de forma automática, así como
// un constructor por defecto
data class Mago(var name: String) : Serializable {

    override fun toString() = name
}