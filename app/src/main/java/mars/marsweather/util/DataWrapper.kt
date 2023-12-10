package mars.marsweather.util

sealed class DataWrapper<T>(
    private val code: Int? = null,
    private val body: T? = null,
    private val errorMessage:String? = null
) {
    class Content<T>(private val data: T) : DataWrapper<T>(body = data) {
        fun getData(): T = data
    }

    class Error<T>(private val msg: String) : DataWrapper<T>(errorMessage = msg){
        fun getErrorMsg():String = msg
    }

    class Loading<T>():DataWrapper<T>()
}
