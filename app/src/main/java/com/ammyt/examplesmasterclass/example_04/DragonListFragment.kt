package com.ammyt.examplesmasterclass.example_04


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_01.Mago
import com.ammyt.examplesmasterclass.example_01.Model_dragon


class DragonListFragment : Fragment() {

    private lateinit var root: View

    // Constructor del Fragment
    companion object {
        private val DRAGON_LIST_ARGUMENT = "DRAGON_LIST_ARGUMENT"

        fun newInstance(dragonList: Model_MagosList): DragonListFragment {
            // La forma de añadir "extras" al constructor es algo diferent en los fragment
            val arguments = Bundle()
            arguments.putSerializable(DRAGON_LIST_ARGUMENT, dragonList)

            // Constructor por defecto al que le añadimos nuestros extras
            val dragonListFragment = DragonListFragment()
            dragonListFragment.arguments = arguments

            return dragonListFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Los fragment no viven por si solos, están contenidos en las vistas de una actividad
        // Por ello, no tienen contexto. Para poder hacer referencia al layout/vista que implementa
        // nos pasan un "inflater", que nos permitirá hacerlo.
        // Hay que fijarse que este método nos pide devolver la vista que vamos a mostrar (la
        // actividad que nos contiene lo necesita)... así que la devolvemos. Podemos almancenarla
        // en una variable para poder hacer referencia luego a ella.

        // Esto es lo que nos crea por defecto
        //return inflater!!.inflate(R.layout.fragment_dragon_list, container, false)

        // if (inflater != null)

        inflater?.let {
            root = it.inflate(R.layout.fragment_dragon_list, container, false)

            // Obtenemos la lista de dragones que nos han pasado en el constructor
            val dragons = arguments.getSerializable(DRAGON_LIST_ARGUMENT) as? Model_MagosList

            // Hacemos un IBOutlet de nuestra lista en el Layout
            val dragonList = root.findViewById<ListView>(R.id.dragon_list)

            // Al igual que iOS, la lista/tabla necesita un dataSource/adapter que le diga qué
            // debe mostrar...
            val adapter = ArrayAdapter<Mago>(
                    activity,       // Contexto: el de un fragment es el de su actividad contenedora
                    android.R.layout.simple_list_item_1,    // Un tipo de adapter genérico pregenerado
                    dragons?.toArray()
            )

            dragonList.adapter = adapter


            // Por supuesto, ponemos un listener a nuestra lista para detectar la pulsación en cada celda
            dragonList.setOnItemClickListener { parent, view, position, id ->

            }
        }

        return root
    }

}
