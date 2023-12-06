package mars.marsweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StartVm:ViewModel() {
    private val _state = MutableLiveData<String>()
    val state = _state as LiveData<String>

    private val _imageMarsUrl = MutableLiveData<String>()
    val imageMarsUrl = _imageMarsUrl as LiveData<String>

    private val clientNasa = RetrofitClient()

    init {
        _state.value = "Init"
    }

    fun loadPhotoFromMars(){
        viewModelScope.launch {
            val response = clientNasa.loadPhotos()
            if(response?.code()==200){
                val body = response.body()
                body?.let {
                    _imageMarsUrl.value = it.photos[0].img_src
                }
            }
            else{
                _state.value = response?.code().toString()
            }
        }
    }
}