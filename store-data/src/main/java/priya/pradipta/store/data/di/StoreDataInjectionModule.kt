package priya.pradipta.store.data.di

import androidx.room.Room
import org.koin.dsl.module
import priya.pradipta.store.data.datasource.StoreKtorImpl
import priya.pradipta.store.data.datasource.StoreLocalImpl
import priya.pradipta.store.data.repository.StoreRepositoryImpl
import priya.pradipta.store.data.room.CartDatabase

val storeDatasourceInjectionModule =
    module {
        single { StoreKtorImpl() }
        single { StoreLocalImpl(get()) }
    }

val storeRepositoryInjectionModule =
    module {
        single { StoreRepositoryImpl(get(), get()) }
    }

val storeRoomInjectionModule =
    module {
        single {
            Room
                .databaseBuilder(
                    get(),
                    CartDatabase::class.java,
                    "cart_database",
                ).build()
        }

        single { get<CartDatabase>().cartDao() }
    }
