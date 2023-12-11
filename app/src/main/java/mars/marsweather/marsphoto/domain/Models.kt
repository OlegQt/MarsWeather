package mars.marsweather.marsphoto.domain

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val photos:List<MarsPhoto>
)

data class MarsPhoto(
    val id:String,
    @SerializedName("img_src")
    val imageUrl:String
)