package priya.pradipta.store.product.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.usecase.Login

class LoginViewModel(
    val login: Login,
) : ViewModel() {
    private val _loginState: MutableStateFlow<LoginScreenUIState> = MutableStateFlow(LoginScreenUIState.Initial)
    val loginState: StateFlow<LoginScreenUIState> = _loginState.asStateFlow()

    fun doLogin(
        username: String,
        password: String,
    ) {
        _loginState.value = LoginScreenUIState.Loading
        viewModelScope.launch {
            login(username, password).also { result ->
                Log.d("Teeeeeeeeeeest", "doLogin: $result")
                when (result) {
                    is ResultOf.Success -> {
                        _loginState.value = LoginScreenUIState.OnSuccess
                    }

                    is ResultOf.Failure -> {
                        _loginState.value =
                            LoginScreenUIState.OnFailure(
                                message = result.exception.message ?: "Uncaught Error",
                            )
                    }
                }
            }
        }
    }
}
