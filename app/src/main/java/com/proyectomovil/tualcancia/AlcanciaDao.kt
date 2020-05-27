package com.proyectomovil.tualcancia

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AlcanciaDao {
    @Query("SELECT sum(valor) as total FROM tu_alcancia")
    fun  getTotalAhorrado(): LiveData<List<Int>>

    @Query("insert into tu_alcancia (valor) values (:alcancia)")
    fun insert(alcancia: Int)

    @Query("DELETE FROM tu_alcancia")
    fun deleteAll()
}