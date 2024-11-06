package priya.pradipta.common

sealed class ResultOf<out T> {
    data class Success<out T>(
        val data: T,
    ) : ResultOf<T>()

    data class Failure<out T>(
        val exception: Throwable,
    ) : ResultOf<T>()
}
