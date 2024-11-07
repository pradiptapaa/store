package priya.pradipta.store.product.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.usecase.SaveToCarts
import priya.pradipta.store.product.model.ProductModel

class ProductDetailViewModel(
    val saveToCartUsecase: SaveToCarts,
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProductDetailUIState> =
        MutableStateFlow(
            ProductDetailUIState.Initial,
        )
    val uiState: StateFlow<ProductDetailUIState> = _uiState.asStateFlow()

    fun saveToCart(productModel: ProductModel) {
        _uiState.value = ProductDetailUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            saveToCartUsecase(
                cart =
                    Cart(
                        image = productModel.image,
                        name = productModel.name,
                        price = productModel.price,
                        rating = productModel.rating,
                        count = productModel.count,
                        category = productModel.category,
                        description = productModel.description,
                    ),
            ).also { result ->
                Log.d("Teeeeeeeeeeest", "doLogin: $result")
                when (result) {
                    is ResultOf.Success -> {
                        _uiState.value = ProductDetailUIState.OnSaveToCartSuccess
                    }

                    is ResultOf.Failure -> {
                        _uiState.value =
                            ProductDetailUIState.OnSaveToCartFailure(
                                message = result.exception.message ?: "Uncaught Error",
                            )
                    }
                }
            }
        }
    }

    fun emitEvent(state: ProductDetailUIState)  {
        _uiState.value = state
    }
}
