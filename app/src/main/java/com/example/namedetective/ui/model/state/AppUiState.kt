package com.example.namedetective.ui.model.state

import com.example.namedetective.data.api.NameData

data class AppUiState(
    val searchResult: NameData? = null,
    val searchError: String = "",
    val history: List<NameData> = listOf()
)