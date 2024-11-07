package priya.pradipta.store.cart.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.usecase.GetCarts
import priya.pradipta.store.product.list.ProductListUIState
import priya.pradipta.store.product.model.ProductModel

class CartViewModel(
    private val getCartsUseCase: GetCarts,
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProductListUIState> =
        MutableStateFlow(
            ProductListUIState.Initial,
        )
    val uiState: StateFlow<ProductListUIState> = _uiState.asStateFlow()

    fun getCarts() {
        _uiState.value = ProductListUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getCartsUseCase().also { result ->
                Log.d("Teeeeeeeeeeest", "doLogin: $result")
                when (result) {
                    is ResultOf.Success -> {
                        _uiState.value =
                            ProductListUIState.OnSuccess(
                                result.data.map { product ->
                                    ProductModel(
                                        name = product.name,
                                        price = product.price,
                                        image = product.image.toString(),
                                        category = product.category,
                                        rating = product.rating,
                                        count = product.count,
                                        description = product.description,
                                    )
                                },
                            )
                    }

                    is ResultOf.Failure -> {
                        _uiState.value =
                            ProductListUIState.OnFailure(
                                message = result.exception.message ?: "Uncaught Error",
                            )
                    }
                }
            }
        }
    }
}
