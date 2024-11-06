package priya.pradipta.techassessment.product.login

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

sealed class LoginScreenUIState {
    data object Initial : LoginScreenUIState()

    data object Loading : LoginScreenUIState()

    data class OnError(
        val message: String,
    ) : LoginScreenUIState()

    data object OnSuccess : LoginScreenUIState()
}

@Composable
fun LoginScreen(state: LoginScreenUIState = LoginScreenUIState.Initial) {
    when (state) {
        LoginScreenUIState.Initial -> {
            // Show login form or loading spinner
        }

        LoginScreenUIState.Loading -> {
            // Show loading spinner
        }

        is LoginScreenUIState.OnError -> {
            // Show error message
        }

        LoginScreenUIState.OnSuccess -> {
            // Show success message and navigate to product list screen
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image =
                if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        },
    )
}
