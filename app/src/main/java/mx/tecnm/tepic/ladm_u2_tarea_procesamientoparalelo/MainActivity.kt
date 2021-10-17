package mx.tecnm.tepic.ladm_u2_tarea_procesamientoparalelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val h = Hilo(this)

        btniniciar.setOnClickListener {
            try {
                h.start()
            } catch (io: Exception){
                Toast.makeText(this,"Error, el hilo ya habia sido iniciado",Toast.LENGTH_LONG).show()
            }
        }

        btnpausar.setOnClickListener {
            h.pausar()
        }

        btnterminar.setOnClickListener {
            h.terminar()
        }
        btnreiniciar.setOnClickListener {
            h.reiniciar()
        }

    }
}

class Hilo(p:MainActivity):Thread(){
    var posicion = 0
    val puntero = p

    var pausado = false
    var continuarCiclo=true

    val segundos = arrayListOf<String>("10","9","8","7","6","5","4","3","2","1","0")

    fun pausar() {
        pausado = !pausado
    }

    fun terminar() {
        continuarCiclo=false
    }

    fun reiniciar() {
        posicion=1
        puntero.runOnUiThread {
            puntero.txtH.text = "${segundos.get(posicion-1)}"
        }
    }


    override fun run() {
        super.run()

        if (posicion>=11){
            posicion=1
        }

        while (continuarCiclo){
            if (pausado == false){
                puntero.runOnUiThread {
                    puntero.txtH.text = "${segundos.get(posicion-1)}"
                }
                posicion++
            }
            sleep(1000)
        }
    }

}