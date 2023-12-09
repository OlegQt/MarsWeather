package mars.marsweather.marsphoto.domain

import kotlinx.coroutines.flow.Flow
import mars.marsweather.util.DataWrapper

class MarsPhotoControllerImpl(private val repository: MarsPhotoRepository) : MarsPhotoController {
    override suspend fun uploadPhoto(dateOnEarth: String): Flow<DataWrapper<String>> =
        repository.uploadMarsPhoto(dateOnEarth)
}