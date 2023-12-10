package mars.marsweather.marsphoto.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mars.marsweather.marsphoto.domain.MarsPhotoRepository
import mars.marsweather.util.DataWrapper

class MarsPhotoRepositoryImpl(private val networkClient: RetrofitClient):MarsPhotoRepository {
    override fun uploadMarsPhoto(dateOnEarth: String): Flow<DataWrapper<String>> {
        return flow {
            emit(DataWrapper.Loading())

            val response = networkClient.loadPhotos(dateOnEarth)
            response?.let {
                if(it.code()==200){
                    val result = it.body()
                    val photo = result?.photos?.first()?.imageUrl
                    emit(DataWrapper.Content(photo.toString()))
                }
            }
        }.catch {
            emit(DataWrapper.Error(it.message.toString()))
        }
    }
}