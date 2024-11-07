package priya.pradipta.store.product.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import priya.pradipta.store.component.BaseLoading
import priya.pradipta.store.product.list.BaseGap

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
    if (state == LoginScreenUIState.Loading)
        {
            BaseLoading()
        }

    Column {
        BaseGap(16.dp)
        Text(text = "Login", style = MaterialTheme.typography.titleLarge)
        BaseGap(16.dp)
        LoginForm(
            onUsernameChange = { username ->
                // Handle username change
            },
            onPasswordChange = { password ->
                // Handle password change
            },
        )
        BaseGap(16.dp)
        Button(onClick = {
            // Handle login button click
        }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    onUsernameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.padding(8.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                onUsernameChange(it)
            },
            label = { Text("Username") },
            singleLine = true,
            placeholder = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
        )
        BaseGap(8.dp)
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                onPasswordChange(it)
            },
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
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    username: String = "",
    password: String = "",
) {
    Button(
        onClick = onLoginClick,
        modifier = modifier.fillMaxWidth(),
        enabled = username.isNotBlank() && password.isNotBlank(),
    ) {
        Text("Login")
    }
}

@Preview
@Composable
private fun LoginButtonPreview() {
    LoginButton(onLoginClick = {})
}

@Preview
@Composable
private fun LoginFormPreview() {
    LoginForm()
}