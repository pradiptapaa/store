package priya.pradipta.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.viewmodel.ext.android.viewModel
import priya.pradipta.store.cart.CartModel
import priya.pradipta.store.cart.item.BaseFilledIconButton
import priya.pradipta.store.cart.list.CartViewModel
import priya.pradipta.store.component.BaseImageRemote
import priya.pradipta.store.product.detail.ProductDetailScreen
import priya.pradipta.store.product.detail.ProductDetailUIState
import priya.pradipta.store.product.detail.ProductDetailViewModel
import priya.pradipta.store.product.list.BaseGap
import priya.pradipta.store.product.list.ProductListModel
import priya.pradipta.store.product.list.ProductListScreen
import priya.pradipta.store.product.list.ProductListViewModel
import priya.pradipta.store.product.login.LoginModel
import priya.pradipta.store.product.login.LoginScreen
import priya.pradipta.store.product.login.LoginViewModel
import priya.pradipta.store.product.model.ProductModel
import priya.pradipta.store.ui.theme.StoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                        startDestination = ProductListModel,
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
                            var showBottomSheet by remember { mutableStateOf(false) }
                            val sheetState = rememberModalBottomSheetState()

                            Box {
                                ProductListScreen(
                                    productListUIState = uiState,
                                    onClick = { navController.navigate(it) },
                                    title = "Product List",
                                )
                                Row(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp)) {
                                    BaseFilledIconButton(
                                        imageVector = Icons.Filled.ShoppingCart,
                                        onClick = { navController.navigate(CartModel) },
                                    )
                                    BaseGap(8.dp)
                                    BaseFilledIconButton(
                                        imageVector = Icons.Filled.Person,
                                        onClick = { showBottomSheet = true },
                                    )
                                }
                            }

                            if (showBottomSheet) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        showBottomSheet = false
                                    },
                                    sheetState = sheetState,
                                ) {
                                    Column(
                                        modifier =
                                            Modifier
                                                .fillMaxSize()
                                                .verticalScroll(rememberScrollState()),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        BaseImageRemote(
                                            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5k-7Hc--4L4A3-2GRrvZRY8HFOZZ71TpSZA&s",
                                            modifier = Modifier.fillMaxWidth().aspectRatio(1.5f),
                                        )
                                        BaseGap(4.dp)
                                        Text("Hello, I'm Pradipta Priya Adyamika")
                                        BaseGap(4.dp)
                                        Text(
                                            "I don't have enough time to make this project because of work",
                                            textAlign = TextAlign.Center,
                                        )
                                        BaseGap(4.dp)
                                        Text("And I severely need a sleep.")
                                    }
                                }
                            }
                        }
                        composable<ProductModel> { backStackEntry ->
                            val uiState by productDetailViewModel.uiState.collectAsState()
                            val product: ProductModel = backStackEntry.toRoute()
                            ProductDetailScreen(
                                product = product,
                                onClick = { navController.navigateUp() },
                                productDetailState = uiState,
                                onSaveToCart = { productDetailViewModel.saveToCart(product) },
                                onSaveToCartSuccess = {
                                    navController.navigate(CartModel)
                                    productDetailViewModel.emitEvent(ProductDetailUIState.Initial)
                                },
                            )
                        }
                        composable<CartModel> {
                            val uiState by cartViewModel.uiState.collectAsState()
                            cartViewModel.getCarts()
                            ProductListScreen(
                                productListUIState = uiState,
                                onClick = { navController.navigate(it) },
                                title = "Cart List",
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
