package com.proyectomovil.tualcancia

import androidx.lifecycle.LiveData

class AlcanciaRepository (private val alcanciaDao: AlcanciaDao) {

    val totalalcancia: LiveData<List<Int>> = alcanciaDao.getTotalAhorrado()

    suspend fun insert(alcancia: Int){
        alcanciaDao.insert(alcancia )
    }

    fun delete(){
        alcanciaDao.deleteAll()
    }
}