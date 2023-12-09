package mars.marsweather.marsphoto.presentation

sealed class MarsPhotoFragmentState() {
    data class Content(private val imageUrl: String) : MarsPhotoFragmentState()
    data class Error(private val errorMsg: String) : MarsPhotoFragmentState()
    data object Loading : MarsPhotoFragmentState()
}
