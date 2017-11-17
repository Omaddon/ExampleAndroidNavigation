package com.ammyt.examplesmasterclass.example_04

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_05.DragonListFragment2

class MainActivity_landscape : AppCompatActivity(),
        // Implementamos esta inferfaz para poder responder cuando se hace click en la tabla
        DragonListFragment2.OnTableSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        // Como nuestro activity ahora tiene varias opciones en función de si es protrait o landscape,
        // debemos comprobar en cuál nos encontramos. Para ellos, simplemente buscamos qué "huecos"
        // o "placeHolder" tenemos en la vista. RECORDAR: en nuestro Activity en landscape tenemos
        // 2 FrameLayout donde meteremos nuestros fragments: lista y dragonName
        if (findViewById<View>(R.id.dragon_list_landscape) != null) {
            // Tenemos un hueco para la lista... pero hemos creado a algún fragment para el hueco??
            if (fragmentManager.findFragmentById(R.id.dragon_list) == null) {
                // No hemos encontrado ningún fragment para el hueco. Creamos uno
                val dragonListFragment = DragonListFragment2.newInstance(Model_MagosList)

                // Lo añadimos al hueco
                fragmentManager.beginTransaction()
                        .add(R.id.dragon_list_landscape, dragonListFragment)
                        .commit()
            }
        }

        if (findViewById<View>(R.id.dragon_name_landscape) != null) {
            if (fragmentManager.findFragmentById(R.id.dragon_name_fragment) == null) {
                // No hemos encontrado ningún fragment para el hueco. Creamos uno
                val dragonNameFragment = DragonFragment.newInstance()

                // Lo añdimos al hueco
                fragmentManager.beginTransaction()
                        .add(R.id.dragon_name_landscape, dragonNameFragment)
                        .commit()
            }
        }
    }





    override fun onTableSelected(position: Int) {
        // Han pulsado en la tabla. Debemos cambiar el nombre que aparece en el saludo
        // Para hacer esto, debemos mandar un mensaje al fragment... a qué fragment? Vamos a buscarlo
        val saludoFragment = fragmentManager.findFragmentById(R.id.dragon_name_landscape) as? DragonFragment

        saludoFragment?.setMageToSalute(Model_MagosList.getMage(position).name)
    }
}
