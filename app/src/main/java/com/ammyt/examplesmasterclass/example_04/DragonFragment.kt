package com.ammyt.examplesmasterclass.example_04


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_01.Model_dragon
import com.ammyt.examplesmasterclass.example_01.Model_mago
import com.ammyt.examplesmasterclass.example_03.DragonActivity


class DragonFragment : Fragment() {

    private lateinit var root: View
    private lateinit var saludo: TextView

    companion object {
        private var mageSelected: String? = null

        fun newInstance(): DragonFragment {
            return  DragonFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let {
            root = it.inflate(R.layout.fragment_dragon, container, false)

            mageSelected = Model_mago.getMago()

            saludo = root.findViewById(R.id.saludo)

            // Limpiamos lo que pueda haber
            saludo.text = ""

            // Asignamos el nuevo texto
            saludo.text = "Saludos " + mageSelected

            root.findViewById<Button>(R.id.show_dragon).setOnClickListener {
                val dragon = Model_dragon("Golpezas")

                // NO LANZAR DESDE AQUÍ, AVISAR A LA ACTIVIDAD y bla bla bla
                startActivity(DragonActivity.newIntent(activity, dragon, mageSelected))
            }
        }

        return root
    }

    fun setMageToSalute(mageName: String) {
        mageSelected = mageName

        // Limpiamos lo que pueda haber
        saludo.text = ""

        // Asignamos el nuevo texto
        saludo.text = "Saludos " + mageName
    }
}