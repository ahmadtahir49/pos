package com.example.pos

class DrugRepository(private val drugDao: DrugDao) {
    val allDrugs: List<Drug> = drugDao.getAllDrugs()

    suspend fun insertDrug(drug: Drug) {
        drugDao.insertDrug(drug)
    }

    suspend fun updateDrug(drug: Drug) {
        drugDao.updateDrug(drug)
    }

    suspend fun deleteDrug(drug: Drug) {
        drugDao.deleteDrug(drug)
    }

    suspend fun getDrugById(id: Int): Drug {
        return drugDao.getDrugById(id)
    }
}
