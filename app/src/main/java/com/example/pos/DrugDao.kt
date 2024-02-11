package com.example.pos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DrugDao {
    @Query("SELECT * FROM drug_table")
    fun getAllDrugs(): List<Drug>

    @Query("SELECT * FROM drug_table WHERE id = :id")
    fun getDrugById(id: Int): Drug

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrug(drug: Drug)

    @Update
    fun updateDrug(drug: Drug)

    @Delete
    fun deleteDrug(drug: Drug)
}
