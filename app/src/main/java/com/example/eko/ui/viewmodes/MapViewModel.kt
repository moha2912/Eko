package com.example.eko.ui.viewmodes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eko.data.model.db.MapLocationEntity
import com.example.eko.data.repository.DefaultMapRepository
import com.example.eko.other.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Moha on 2022-06-08.
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: DefaultMapRepository
) : ViewModel() {

    private var _getAll = MutableStateFlow<List<MapLocationEntity>>(mutableListOf())
    val getAll = _getAll.asStateFlow()


    init {
        getAllDataFromApi()

        viewModelScope.launch {
            mapRepository.getAllFromDb()
                .catch { exception ->
                    // TODO: show messages as you needed

                    Log.i("TAG", "exception: $exception")
                }.collect { updateList ->
                    Log.i("TAG", ": ")
                    _getAll.emit(updateList)
                }
        }
    }

    fun getAllDataFromApi() {
        viewModelScope.launch {
            val data = mapRepository.getAllFromApi()

            data.collectLatest { resource ->
                when (resource.statusResource) {
                    Status.ERROR -> {
                        // TODO: show messages as you needed
                        Log.d("ERROR", "getAllData: ${resource.errorModel}")
                    }
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        Log.i("SUCCESS", "getAllData: ${resource.data}")
                    }

                }

            }
        }
    }
}

