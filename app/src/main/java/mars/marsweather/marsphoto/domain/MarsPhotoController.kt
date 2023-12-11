package mars.marsweather.marsphoto.domain

import kotlinx.coroutines.flow.Flow
import mars.marsweather.util.DataWrapper

interface MarsPhotoController {
    suspend fun uploadPhoto(date:String):Flow<DataWrapper<String>>
}