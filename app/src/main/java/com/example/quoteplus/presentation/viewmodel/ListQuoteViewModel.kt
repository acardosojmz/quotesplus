package com.example.quoteplus.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteplus.domain.model.QuoteModel
import com.example.quoteplus.domain.model.QuoteUiState
import com.example.quoteplus.domain.usecase.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListQuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Init)
    val uiState: StateFlow<QuoteUiState> = _uiState

    init {
        getQuotes()
    }
    private fun getQuotes() {
        _uiState.value = QuoteUiState.Loading

        viewModelScope.launch {
            val uiState = getQuotesUseCase.getQuotes().first()
            notifyDataState(uiState.data)
            }

        }
    private fun notifyInitState() {
        _uiState.value= QuoteUiState.Init
    }
    private fun notifyLoadingState() {
        _uiState.value = QuoteUiState.Loading
    }

    private fun notifyDataState(data: List<QuoteModel>) {
        _uiState.value= QuoteUiState.Data(data)
    }

    private fun notifyErrorState(error: Throwable) {
        _uiState.value= QuoteUiState.Error(error)
    }
}