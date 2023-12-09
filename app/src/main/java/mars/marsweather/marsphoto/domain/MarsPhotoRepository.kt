package mars.marsweather.marsphoto.domain

import kotlinx.coroutines.flow.Flow
import mars.marsweather.util.DataWrapper

interface MarsPhotoRepository {
    fun uploadMarsPhoto(dateOnEarth: String): Flow<DataWrapper<String>>
}