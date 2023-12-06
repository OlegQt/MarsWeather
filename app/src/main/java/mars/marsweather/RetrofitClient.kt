package mars.marsweather

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var retrofitInstance: Retrofit? = null
    private val baseUrl ="https://api.nasa.gov/"
    private val cameraName = "fhaz"
    private val token = "XR2qedQcfu9pNN7Cam6m9r0hLVunb2LvskC9sW8Z"

    init {
        retrofitInstance = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun loadPhotos():Response<PhotoResponse>?{
        val queryParams = mutableMapOf<String,String>()
        queryParams["api_key"] = token
        queryParams["earth_date"] = "2023-12-01"
        queryParams["page"] = "1"
        queryParams["camera"] = cameraName

        val nasaApi = retrofitInstance?.create(NasaApi::class.java)

        return nasaApi?.loadPhoto(queryParams = queryParams)
    }


}