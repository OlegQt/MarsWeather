package mars.marsweather.marsphoto.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import mars.marsweather.marsphoto.domain.MarsPhotoRepository
import mars.marsweather.util.DataWrapper

class MarsPhotoRepositoryImpl(private val networkClient: RetrofitClient) : MarsPhotoRepository {
    override fun uploadMarsPhoto(dateOnEarth: String): Flow<DataWrapper<String>> {
        return flow<DataWrapper<String>> {
            emit(DataWrapper.Loading())

            val response = networkClient.loadPhotos(dateOnEarth)
            response?.let {
                if (it.isSuccessful) {
                    val photoResult = it.body()?.photos?.firstOrNull()
                    if (photoResult != null) emit(DataWrapper.Content(photoResult.imageUrl))
                    else emit(DataWrapper.Error("Empty response"))
                } else {
                    emit(DataWrapper.Error("Request failed with code ${it.code()}"))
                }
            }

        }.catch {
            emit(DataWrapper.Error("Error"))
        }
    }
}