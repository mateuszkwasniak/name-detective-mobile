package com.example.namedetective.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.namedetective.data.api.ApiInterface
import com.example.namedetective.data.api.NameData
import com.example.namedetective.ui.model.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.nationalize.io/"

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())

    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var nameEntered by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun updateNameEntered(name: String) {
        nameEntered = name
    }

    fun updateSearchResult(nameData: NameData?) {
        _uiState.update { currentState ->
            currentState.copy(
                searchResult = nameData,
                history = if (nameData !== null) {
                    uiState.value.history.toList() + nameData
                } else currentState.history
            )
        }
    }

    fun updateLoading(loading: Boolean) {
        isLoading = loading
    }

    fun updateSearchError(error: String) {
        _uiState.update { currentState -> currentState.copy(searchError = error) }
    }

    fun fetchNameDetails() {
        if (nameEntered != "") {
            _uiState.update { currentState ->
                currentState.copy(searchError = "", searchResult = null)
            };
            updateLoading(true);

            val retrofitBuilder =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build().create(ApiInterface::class.java)
            val retrofitData = retrofitBuilder.getData(nameEntered)
            retrofitData.enqueue(object : Callback<NameData?> {
                override fun onResponse(call: Call<NameData?>, response: Response<NameData?>) {

                    val responseBody = response.body()
                    if (responseBody?.country?.size === 0) {
                        updateSearchError("Unfortunately we found no results for $nameEntered 😕")
                    } else {
                        updateSearchResult(responseBody);
                    }

                    updateLoading(false);
                    updateNameEntered("")

                }

                override fun onFailure(call: Call<NameData?>, t: Throwable) {
                    updateLoading(false);
                    updateNameEntered("")
                }
            })
        } else return
    }

}
