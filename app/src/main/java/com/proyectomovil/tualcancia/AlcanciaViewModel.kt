package com.proyectomovil.tualcancia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlcanciaViewModel (application: Application): AndroidViewModel(application) {

    private val repository: AlcanciaRepository

    val totalAlcancia: LiveData<List<Int>>

    init {
        val alcanciaDao = AlcanciaRoomDatabase.getDatabase(application, viewModelScope).alcanciaDao()
        repository = AlcanciaRepository(alcanciaDao)
        totalAlcancia = repository.totalalcancia
    }

    fun insert(alcancia: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(alcancia)
    }

    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        repository.delete()
    }
}