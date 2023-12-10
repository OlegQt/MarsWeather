package mars.marsweather.marsphoto.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mars.marsweather.marsphoto.data.MarsPhotoRepositoryImpl
import mars.marsweather.marsphoto.data.RetrofitClient
import mars.marsweather.marsphoto.domain.MarsPhotoControllerImpl
import mars.marsweather.util.DataWrapper
import kotlin.random.Random

class StartVm : ViewModel() {
    private val _fragmentState =
        MutableStateFlow<MarsPhotoFragmentState>(MarsPhotoFragmentState.Content("URL"))
    val fragmentState = _fragmentState as StateFlow<MarsPhotoFragmentState>

    private var dateOnEarth:String = "2023-10-17"

    private val clientNasa = RetrofitClient()
    private val photoMarsRepo = MarsPhotoRepositoryImpl(networkClient = clientNasa)
    private val photoMarsController = MarsPhotoControllerImpl(repository = photoMarsRepo)

    fun uploadPhoto() {
        viewModelScope.launch {
            val result = photoMarsController.uploadPhoto(dateOnEarth)
            result.collect { data ->
                when (data) {
                    is DataWrapper.Loading -> {
                        _fragmentState.update { MarsPhotoFragmentState.Loading }
                    }

                    is DataWrapper.Content -> {
                        _fragmentState.update { MarsPhotoFragmentState.Content(data.getData()) }
                    }

                    else -> {
                    }
                }
            }
        }
    }

    fun chooseNewDate(newDate:String){
        this.dateOnEarth = newDate
    }
}