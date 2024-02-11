package com.example.pos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DrugViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DrugRepository
    val allDrugs: LiveData<List<Drug>>

    init {
        val drugDao = DrugDatabase.getDatabase(application).drugDao()
        repository = DrugRepository(drugDao)
        allDrugs = repository.allDrugs
    }

    fun insertDrug(drug: Drug) = viewModelScope.launch {
        repository.insertDrug(drug)
    }

    fun updateDrug(drug: Drug) = viewModelScope.launch {
        repository.updateDrug(drug)
    }

    fun deleteDrug(drug: Drug) = viewModelScope.launch {
        repository.deleteDrug(drug)
    }

    fun getDrugById(id: Int) = viewModelScope.launch {
        repository.getDrugById(id)
    }
}
