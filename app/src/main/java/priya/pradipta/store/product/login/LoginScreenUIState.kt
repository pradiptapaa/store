package priya.pradipta.store.product.login

sealed class LoginScreenUIState {
    data object Initial : LoginScreenUIState()

    data object Loading : LoginScreenUIState()

    data class OnFailure(
        val message: String,
    ) : LoginScreenUIState()

    data object OnSuccess : LoginScreenUIState()
}