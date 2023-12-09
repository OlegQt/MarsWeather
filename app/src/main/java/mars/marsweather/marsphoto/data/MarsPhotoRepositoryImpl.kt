package mars.marsweather.marsphoto.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mars.marsweather.marsphoto.domain.MarsPhotoRepository
import mars.marsweather.util.DataWrapper

class MarsPhotoRepositoryImpl(private val networkClient: RetrofitClient):MarsPhotoRepository {
    override fun uploadMarsPhoto(dateOnEarth: String): Flow<DataWrapper<String>> {
        Log.e("LOG","repository")
        return flow {
            emit(DataWrapper.Loading())
        }
    }
}