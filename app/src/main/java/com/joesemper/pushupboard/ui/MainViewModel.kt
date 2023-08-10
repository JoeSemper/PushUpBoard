package com.joesemper.pushupboard.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesemper.pushupboard.domain.use_case.InitiateDatabaseUseCase
import kotlinx.coroutines.launch


class MainViewModel(
    private val initiateDatabase: InitiateDatabaseUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    init {
        initDatabase()
        setIsLoadedUiState()
    }

    private fun initDatabase() {
        viewModelScope.launch {
            initiateDatabase()
        }
    }

    private fun setIsLoadedUiState() {
        uiState = uiState.copy(isLoaded = true)
    }
}

data class MainUiState(
    val isLoaded: Boolean = false,
)