package org.greenfroyo.composekoans.insightj

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.greenfroyo.composekoans.insightj.model.Book

class ISJSearchVM(val repository: ISJRepository = ISJRepository()) : ViewModel() {
    private val _state = MutableStateFlow(ISJSearchState())
    val state = _state.asStateFlow()

    init {
        getBooks()
    }


    fun getBooks(){
        viewModelScope.launch {
            repository.getBooks().collect { books ->
                _state.update {
                    it.copy(books = books)
                }
            }
        }
    }
}

data class ISJSearchState(
    val searchKeywords: String = "",
    val books: List<Book> = emptyList()
)



