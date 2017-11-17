package com.ammyt.examplesmasterclass.example_03

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ammyt.examplesmasterclass.R
import com.ammyt.examplesmasterclass.example_01.Model_dragon

class DragonActivity : AppCompatActivity() {

    companion object {
        private val ETIQUETA_PARA_DATOS_EXTRA = "ETIQUETA_PARA_DATOS_EXTRA"
        private val MAGO_EXTRA = "MAGO_EXTRA"

        // "Constructor" de la clase
        fun newIntent(context: Context, dragon: Model_dragon, mago: String?): Intent {
            val intent = Intent(context, DragonActivity::class.java)

            // Le añadimos algunos datos extra para que nos lo pasen en el constructor
            intent.putExtra(ETIQUETA_PARA_DATOS_EXTRA, dragon)
            intent.putExtra(MAGO_EXTRA, mago)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dragon)

        // Extraemos los datos que nos pasaron en el constructor
        val dragon = intent.getSerializableExtra(ETIQUETA_PARA_DATOS_EXTRA) as? Model_dragon
        val mago = intent.getStringExtra(MAGO_EXTRA)
        //val dragonName = dragon?.dragonName()
        val dragonName = dragon?.dragonName(mago)

        // Pintamos los datos en la vista asociada
        val labelName = findViewById<TextView>(R.id.dragon_name)
        labelName.text = dragonName

        // Hacemos un IBOutlet de nuestro botón y le ponemos un listener
        findViewById<Button>(R.id.back).setOnClickListener { v: View? ->

            // Si tuvieramos que devolver algo, se haría a través de los extras de un intent
            // Ejemplo:
            /*
            val returnIntent = Intent()
            returnIntent.putExtra("ETIQUETA", table)

            setResult(Activity.RESULT_OK, returnIntent)
            */

            // Cuando nos hagan click, hacemos un "pop()". Es decir, terminamos esta actividad,
            // desapilamos y volvemos a la actividad anterior
            finish()
        }
    }
}
