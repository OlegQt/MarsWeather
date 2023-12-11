package mars.marsweather.marsphoto.presentation

sealed class MarsPhotoFragmentState() {
    data class Content(val imageUrl: String) : MarsPhotoFragmentState()
    data class Error(val errorMsg: String) : MarsPhotoFragmentState()
    data object Loading : MarsPhotoFragmentState()
}
