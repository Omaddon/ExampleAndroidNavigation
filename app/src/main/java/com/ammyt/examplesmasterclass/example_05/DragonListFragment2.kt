package com.ammyt.examplesmasterclass.example_05


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_01.Mago
import com.ammyt.examplesmasterclass.example_04.Model_MagosList


class DragonListFragment2 : Fragment() {

    private lateinit var root: View

    // Aquí guardaremos una referencia de la actividad que contiene al Fragment y que implementa
    // la interfaz de escucha para el listener
    private var onTableSelectedListener: OnTableSelectedListener? = null

    // Constructor del Fragment
    companion object {
        private val MAGOS_LIST_ARGUMENT = "MAGOS_LIST_ARGUMENT"

        fun newInstance(magosList: Model_MagosList): DragonListFragment2 {
            // La forma de añadir "extras" al constructor es algo diferent en los fragment
            val arguments = Bundle()
            arguments.putSerializable(MAGOS_LIST_ARGUMENT, magosList)

            // Constructor por defecto al que le añadimos nuestros extras
            val dragonListFragment = DragonListFragment2()
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
        // return inflater!!.inflate(R.layout.fragment_dragon_list, container, false)

        // if (inflater != null)

        inflater?.let {
            root = it.inflate(R.layout.fragment_dragon_list, container, false)

            // Obtenemos la lista de dragones que nos han pasado en el constructor
            val dragons = arguments.getSerializable(MAGOS_LIST_ARGUMENT) as? Model_MagosList

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
                // El Fragment NUNCA debe tener ninguna lógica de navegación. El Fragment es tonto.
                // Por ello, informa a su actividad contenedora que se ha pulsado en una celda.
                // La actividad sabrá qué hacer.

                // Para comunicarnos con la actividad, usaremos un protocolo/interfaz.
                onTableSelectedListener?.onTableSelected(position)
            }
        }

        return root
    }

    // Cuano un Fragment se crea ya asocia a una actividad, se llama a este método. El contexto que
    // nos pasan en la llamada será el de nuestra actividad contenedora. Es aquí donde aprovechamos
    // para guardar una referencia de la actividad contenedora y preguntar si implementa nuestra
    // interfaz para el listener de la lista
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnTableSelectedListener) {
            onTableSelectedListener = context
        }
    }

    // Cuando el Fragmento se destruye o muere la actividad contenedora, se llama a este método. Aquí
    // aprovechamos para "limpiar" nuestra referencia a la actividad que nos contenía. Hay que hacer
    // esto porque puede que el contexto de la actividad cambie cuando nos vuelva a usarse este Fragment
    override fun onDetach() {
        super.onDetach()

        onTableSelectedListener = null
    }

    interface OnTableSelectedListener {
        fun onTableSelected(position: Int)
    }

}
