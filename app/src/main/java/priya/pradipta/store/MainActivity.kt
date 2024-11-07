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
import androidx.navigation.toRoute
import org.koin.androidx.viewmodel.ext.android.viewModel
import priya.pradipta.store.cart.CartModel
import priya.pradipta.store.cart.list.CartViewModel
import priya.pradipta.store.product.detail.ProductDetailScreen
import priya.pradipta.store.product.detail.ProductDetailViewModel
import priya.pradipta.store.product.list.ProductListModel
import priya.pradipta.store.product.list.ProductListScreen
import priya.pradipta.store.product.list.ProductListViewModel
import priya.pradipta.store.product.login.LoginModel
import priya.pradipta.store.product.login.LoginScreen
import priya.pradipta.store.product.login.LoginViewModel
import priya.pradipta.store.product.model.ProductModel
import priya.pradipta.store.ui.theme.StoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel by viewModel()
            val productListViewModel: ProductListViewModel by viewModel()
            val productDetailViewModel: ProductDetailViewModel by viewModel()
            val cartViewModel: CartViewModel by viewModel()
            StoreTheme {
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = CartModel,
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
                            val uiState by productListViewModel.uiState.collectAsState()
                            ProductListScreen(
                                productListUIState = uiState,
                                onClick = { navController.navigate(it) },
                            )
                        }
                        composable<ProductModel> { backStackEntry ->
                            val uiState by productDetailViewModel.uiState.collectAsState()
                            val product: ProductModel = backStackEntry.toRoute()
                            ProductDetailScreen(
                                product = product,
                                onClick = { navController.navigateUp() },
                                productDetailState = uiState,
                                onSaveToCart = { productDetailViewModel.saveToCart(product) },
                                onSaveToCartSuccess = { navController.navigate(CartModel) },
                            )
                        }
                        composable<CartModel> {
                            val uiState by cartViewModel.uiState.collectAsState()
                            ProductListScreen(
                                productListUIState = uiState,
                                onClick = { navController.navigate(it) },
                            )
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
