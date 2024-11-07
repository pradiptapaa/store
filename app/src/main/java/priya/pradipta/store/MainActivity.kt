package priya.pradipta.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import priya.pradipta.store.product.list.ProductListModel
import priya.pradipta.store.product.list.ProductListScreen
import priya.pradipta.store.product.login.LoginModel
import priya.pradipta.store.product.login.LoginScreen
import priya.pradipta.store.product.login.LoginViewModel
import priya.pradipta.store.ui.theme.StoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel by viewModel()
            StoreTheme {
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = LoginModel,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable<LoginModel> {
                            val uiState by loginViewModel.loginState.collectAsState()
                            LoginScreen(onLoginClick = { params ->
                                loginViewModel.doLogin(
                                    username = params.username,
                                    password = params.password,
                                )
                            }, state = uiState, onLoginSuccess = {
                                navController.navigate(ProductListModel)
                            })
                        }
                        composable<ProductListModel> {
                            ProductListScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    StoreTheme {
        Greeting("Android")
    }
}
