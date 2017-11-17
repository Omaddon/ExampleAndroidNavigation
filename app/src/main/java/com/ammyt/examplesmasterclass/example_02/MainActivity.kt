package com.ammyt.examplesmasterclass.example_02

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_01.Model_dragon
import com.ammyt.examplesmasterclass.example_01.Model_mago
import com.ammyt.examplesmasterclass.example_03.DragonActivity
import com.ammyt.examplesmasterclass.example_04.Model_MagosList

class MainActivity : AppCompatActivity() {

    companion object {
        private var mageSelected: String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Con esto le decimos qué vista tiene asociada. Fijarse en LAYOUT, no ID.
        setContentView(R.layout.activity_main_inicial)


        // Inicializamos nuestro mago con el método del Singletone
        mageSelected = Model_mago.getMago()     // Carolinus, nuestro único Mago


        // Hacemos un IBOUTLET de nuestro texto
        val saludo = findViewById<TextView>(R.id.saludo)


        // Accedemos a las propiedades del IBOUTLET para actualizar la vista con nuestro modelo
        saludo.text = "Saludos " + mageSelected


        button()
    }







    private fun button() {

        val button = findViewById<Button>(R.id.show_dragon)

        // Añadimos un listener al botón para atender las pulsaciones sobre éste
        button.setOnClickListener {
            val dragon = Model_dragon("Golpezas")

            // Lanzamos la nueva actividad con su constructor personalizado
            startActivity(DragonActivity.newIntent(this, dragon, Model_MagosList.getMage(0).name))

            // Esto es otro modo de lanzar la actividad, cuando esperamos que nos devuelva algo
            //startActivityForResult(DragonActivity.newIntent(this, dragon), 1)
        }
    }

    // Método que se llamará cuando volvamos de una llamada a 'startActivityForResult'
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Comprobamos que el requestCode que nos llega es el mismo con el que lanzamos la actividad
        if (requestCode == 1) {
            // La actividad nos devolverá un resultado. Comprobaos dicho resultado
            if (resultCode == Activity.RESULT_OK){
                // En data nos habrán devuelto algo, en forma de "extra" dentro del intent de data
                data?.getSerializableExtra("NOMBRE DE LA KEY CON LA QUE GUARDARON EL DATO")
            }
        }
    }
}
