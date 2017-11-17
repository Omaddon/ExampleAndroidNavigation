package com.ammyt.examplesmasterclass.example_04

import com.ammyt.examplesmasterclass.example_01.Mago
import java.io.Serializable


object Model_MagosList : Serializable {

    var magosList: List<Mago> = mutableListOf(
            Mago("Carolinus"),
            Mago("Solarius"),
            Mago("Lo Tae Zhao"),
            Mago("Ommadon"))

    // Método para transformar la lista en un array que pueda manejar el adaoter de la listView
    fun toArray() = magosList.toTypedArray()

    fun getMage(position: Int): Mago {
        return magosList.get(position)
    }

}