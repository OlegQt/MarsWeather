package mars.marsweather.marsphoto.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mars.marsweather.marsphoto.data.MarsPhotoRepositoryImpl
import mars.marsweather.marsphoto.data.RetrofitClient
import mars.marsweather.marsphoto.domain.MarsPhotoControllerImpl
import mars.marsweather.util.DataWrapper

class StartVm : ViewModel() {
    private val _state = MutableLiveData<String>()
    val state = _state as LiveData<String>

    private val _imageMarsUrl = MutableLiveData<String>()
    val imageMarsUrl = _imageMarsUrl as LiveData<String>

    private val _fragmentState =
        MutableStateFlow<MarsPhotoFragmentState>(MarsPhotoFragmentState.Loading)
    val fragmentState = _fragmentState.asStateFlow()

    private val clientNasa = RetrofitClient()
    private val photoMarsRepo = MarsPhotoRepositoryImpl(networkClient = clientNasa)
    private val photoMarsController = MarsPhotoControllerImpl(repository = photoMarsRepo)

    fun uploadPhoto() {
        viewModelScope.launch {
            val result = photoMarsController.uploadPhoto("2023-10-17")
            result.collect {
                when (it) {
                    is DataWrapper.Loading -> {
                        _fragmentState.update { MarsPhotoFragmentState.Loading }
                    }

                    else -> {}
                }
            }
        }
    }

    fun loadPhotoFromMars(date: String) {
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            _state.postValue(throwable.message)
        }
        viewModelScope.launch(errorHandler) {
            val response = clientNasa.loadPhotos(date)
            if (response?.code() == 200) {
                val body = response.body()
                body?.let {
                    _imageMarsUrl.value = it.photos[0].imageUrl
                }
            } else {
                _state.postValue(response?.code().toString())
            }
        }
    }
}