package priya.pradipta.store.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import priya.pradipta.store.product.list.ProductListViewModel
import priya.pradipta.store.product.login.LoginViewModel

val storeAppInjectionModule =
    module {
        viewModel { LoginViewModel(get()) }
        viewModel { ProductListViewModel(get()) }
    }
