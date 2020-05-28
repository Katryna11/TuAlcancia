package com.proyectomovil.tualcancia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Alcancia::class], version = 1, exportSchema = false)
abstract class AlcanciaRoomDatabase : RoomDatabase() {

    abstract fun alcanciaDao() : AlcanciaDao

    companion object {
        @Volatile
        private var INSTANCE : AlcanciaRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ) : AlcanciaRoomDatabase {

            return INSTANCE ?: synchronized( this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlcanciaRoomDatabase::class.java,
                    "alcancia"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AlcanciaDatabseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class AlcanciaDatabseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback () {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch (Dispatchers.IO) {
                        populateDatabase(database.alcanciaDao())
                    }
                }
            }
        }

        fun populateDatabase(alcanciaDao: AlcanciaDao) {
            /*alcanciaDao.deleteAll()*/
            var valor = 0
            alcanciaDao.insert(valor)
        }
    }
}