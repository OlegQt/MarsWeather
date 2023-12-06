package mars.marsweather

data class PhotoResponse(
    val photos:List<MarsPhoto>
)

data class MarsPhoto(
    val id:String,
    val img_src:String
)