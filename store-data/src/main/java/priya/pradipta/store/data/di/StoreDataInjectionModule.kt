package priya.pradipta.store.data.di

import androidx.room.Room
import org.koin.dsl.module
import priya.pradipta.store.data.datasource.AuthKtorImpl
import priya.pradipta.store.data.datasource.StoreKtorImpl
import priya.pradipta.store.data.datasource.StoreLocalImpl
import priya.pradipta.store.data.repository.AuthRepositoryImpl
import priya.pradipta.store.data.repository.StoreRepositoryImpl
import priya.pradipta.store.data.room.CartDao
import priya.pradipta.store.data.room.CartDatabase
import priya.pradipta.store.domain.datasource.AuthRemoteDatasource
import priya.pradipta.store.domain.datasource.StoreLocalDatasource
import priya.pradipta.store.domain.datasource.StoreRemoteDatasource
import priya.pradipta.store.domain.repository.AuthRepository
import priya.pradipta.store.domain.repository.StoreRepository

val storeDatasourceInjectionModule =
    module {
        single<StoreRemoteDatasource> { StoreKtorImpl() }
        single<StoreLocalDatasource> { StoreLocalImpl(get()) }
        single<AuthRemoteDatasource> { AuthKtorImpl() }
    }

val storeRepositoryInjectionModule =
    module {
        single<AuthRepository> { AuthRepositoryImpl(get()) }
        single<StoreRepository> { StoreRepositoryImpl(get(), get()) }
    }

val storeRoomInjectionModule =
    module {
        single<CartDatabase> {
            Room
                .databaseBuilder(
                    get(),
                    CartDatabase::class.java,
                    "cart_database",
                ).build()
        }

        single<CartDao> { get<CartDatabase>().cartDao() }
    }
