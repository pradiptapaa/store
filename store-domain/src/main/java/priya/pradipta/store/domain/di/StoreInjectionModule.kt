package priya.pradipta.store.domain.di

import org.koin.dsl.module
import priya.pradipta.store.domain.usecase.GetCarts
import priya.pradipta.store.domain.usecase.GetProducts
import priya.pradipta.store.domain.usecase.Login
import priya.pradipta.store.domain.usecase.SaveToCarts

val storeInjectionModule =
    module {
        single { GetCarts(get()) }
        single { GetProducts(get()) }
        single { Login(get()) }
        single { SaveToCarts(get()) }
    }
