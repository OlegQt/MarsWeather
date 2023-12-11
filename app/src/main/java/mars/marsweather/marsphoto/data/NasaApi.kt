package mars.marsweather.marsphoto.data

import mars.marsweather.marsphoto.domain.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NasaApi {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun loadPhoto(@QueryMap queryParams: Map<String, String>): Response<PhotoResponse>
}