package com.proyectomovil.tualcancia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newvalorActivityRequestCode = 1
    private lateinit var alcanciaViewModel: AlcanciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AlcanciaListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        alcanciaViewModel = ViewModelProvider(this).get(AlcanciaViewModel::class.java)

        alcanciaViewModel.totalAlcancia.observe(this, Observer { total ->
            total?.let { adapter.setValor(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewValorActivity::class.java)
            startActivityForResult(intent, newvalorActivityRequestCode)
        }

        val romper_alcancia = findViewById<ImageButton>(R.id.romper_alcancia)

        romper_alcancia.setOnClickListener {

            Toast.makeText(
                applicationContext,
                R.string.rompe_alcancia,
                Toast.LENGTH_LONG
            ).show()
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newvalorActivityRequestCode && resultCode == Activity.RESULT_OK){
            intentData?.let { data ->
                val valor = data.getIntExtra(NewValorActivity.EXTRA_REPLY, 1)


                alcanciaViewModel.insert(valor)
                Unit
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}