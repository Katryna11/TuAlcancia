package com.proyectomovil.tualcancia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tu_alcancia")
class Alcancia(@PrimaryKey(autoGenerate = false) val id: Int, val valor: Int)