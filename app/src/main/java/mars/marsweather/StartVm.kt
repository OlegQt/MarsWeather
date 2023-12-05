package mars.marsweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartVm:ViewModel() {
    private val _state = MutableLiveData<String>()
    val state = _state as LiveData<String>

    init {
        _state.value = "Init"
    }
}