package mars.marsweather.util

sealed class DataWrapper<T>(
    private val code: Int? = null,
    private val body: T? = null
) {
    class Content<T>(private val data: T) : DataWrapper<T>(body = data) {
        fun getData(): T = data
    }

    class Error<T>(private val errorCode: Int) : DataWrapper<T>(code = errorCode){
        fun getErrorCode():Int = errorCode
    }

    class Loading<T>():DataWrapper<T>()
}
