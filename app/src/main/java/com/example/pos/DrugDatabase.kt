package com.example.pos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Drug::class], version = 1, exportSchema = false)
abstract class DrugDatabase : RoomDatabase() {
    abstract fun drugDao(): DrugDao

    companion object {
        @Volatile
        private var INSTANCE: DrugDatabase? = null

        fun getDatabase(context: Context): DrugDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrugDatabase::class.java,
                    "drug_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
