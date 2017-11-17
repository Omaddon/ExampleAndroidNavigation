package com.ammyt.examplesmasterclass.example_01

import java.io.Serializable

data class Model_dragon(
        var name: String,
        var edad: Int?) : Serializable {

    // Con data class nos genera los getter y setter de forma automática, así como
    // un constructor por defecto

    constructor(name: String) : this (name, null)

    fun dragonName(): String {
        return name + " de Carolinus"
    }

    fun dragonName(mago: String): String {
        return name + " " + mago
    }

    // Lo mismo de arriba, pero "al estilo kotlin"
    fun dragonNameKotlin() = name + " de Carolinus"

    // Para que se muestre bien en la lista
    override fun toString(): String {
        return name
    }
}