package org.greenfroyo.composekoans

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun navigate(destination: NavigationDestination){
        _state.update {
            it.copy(navDes = destination)
        }
    }
}

data class MainState(val num: Int = 0, val navDes: NavigationDestination? = null)