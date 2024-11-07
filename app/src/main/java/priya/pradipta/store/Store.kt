package priya.pradipta.store

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import priya.pradipta.store.data.di.storeDatasourceInjectionModule
import priya.pradipta.store.data.di.storeHttpInjectionModule
import priya.pradipta.store.data.di.storeRepositoryInjectionModule
import priya.pradipta.store.data.di.storeRoomInjectionModule
import priya.pradipta.store.di.storeAppInjectionModule
import priya.pradipta.store.domain.di.storeUsecaseInjectionModule

class Store : Application() {
    override fun onCreate() {
        // Initialize your application-wide dependencies here
        super.onCreate()
        startKoin {
            androidContext(this@Store)
            modules(
                storeAppInjectionModule,
                storeRepositoryInjectionModule,
                storeDatasourceInjectionModule,
                storeUsecaseInjectionModule,
                storeRoomInjectionModule,
                storeHttpInjectionModule,
            )
        }
    }
}
