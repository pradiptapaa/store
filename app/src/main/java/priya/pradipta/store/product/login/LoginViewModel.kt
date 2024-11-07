package priya.pradipta.store.product.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.usecase.Login

class LoginViewModel(
    val login: Login,
) : ViewModel() {
    private val loginState = mutableStateOf<LoginScreenUIState>(LoginScreenUIState.Initial)

    fun doLogin(
        username: String,
        password: String,
    ) {
        loginState.value = LoginScreenUIState.Loading
        viewModelScope.launch {
            login(username, password).also { result ->
                when (result) {
                    is ResultOf.Success -> {
                        loginState.value = LoginScreenUIState.OnSuccess
                        loginState.value = LoginScreenUIState.Initial
                    }

                    is ResultOf.Failure -> {
                        loginState.value =
                            LoginScreenUIState.OnFailure(
                                message = result.exception.message ?: "Uncaught Error",
                            )
                    }
                }
            }
        }
    }
}
