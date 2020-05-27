package com.proyectomovil.tualcancia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class NewValorActivity : AppCompatActivity() {
    private lateinit var editValorView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_valor)

        val diez_mil = findViewById<ImageButton>(R.id.diez_mil)
        val veinte_mil = findViewById<ImageButton>(R.id.veinte_mil)
        val cincuenta_mil = findViewById<ImageButton>(R.id.cincuenta_mil)
        val cien_mil = findViewById<ImageButton>(R.id.cien_mil)

        diez_mil.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(diez_mil.getTag().toString())){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val valor = diez_mil.getTag().toString()
                val valor1 = Integer.parseInt(valor)

                replyIntent.putExtra(EXTRA_REPLY, valor1)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        veinte_mil.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(veinte_mil.getTag().toString())){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val valor = veinte_mil.getTag().toString()
                val valor1 = Integer.parseInt(valor)

                replyIntent.putExtra(EXTRA_REPLY, valor1)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        cincuenta_mil.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(cincuenta_mil.getTag().toString())){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val valor = cincuenta_mil.getTag().toString()
                val valor1 = Integer.parseInt(valor)

                replyIntent.putExtra(EXTRA_REPLY, valor1)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        cien_mil.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(cien_mil.getTag().toString())){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val valor = cien_mil.getTag().toString()
                val valor1 = Integer.parseInt(valor)

                replyIntent.putExtra(EXTRA_REPLY, valor1)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }





    }

    companion object {
        const val EXTRA_REPLY = "ALGO"
    }
}