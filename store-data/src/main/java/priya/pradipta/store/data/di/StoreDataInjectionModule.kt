package priya.pradipta.store.data.di

import android.util.Log
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
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
        single<AuthRemoteDatasource> { AuthKtorImpl(get()) }
        single<StoreRemoteDatasource> { StoreKtorImpl(get()) }
        single<StoreLocalDatasource> { StoreLocalImpl(get()) }
    }

val storeRepositoryInjectionModule =
    module {
        single<AuthRepository> { AuthRepositoryImpl(get()) }
        single<StoreRepository> { StoreRepositoryImpl(get(), get()) }
    }

private const val TIME_OUT = 6000

val storeHttpInjectionModule =
    module {
        single {
            HttpClient(OkHttp) {

                HttpResponseValidator {
                    validateResponse { response: HttpResponse ->
                        val statusCode = response.status.value

                        println("HTTP status: $statusCode")

                        when (statusCode) {
                            in 300..399 -> throw RedirectResponseException(response, "")
                            in 400..499 -> throw ClientRequestException(response, "")
                            in 500..599 -> throw ServerResponseException(response, "")
                        }

                        if (statusCode >= 600) {
                            throw ResponseException(response, "")
                        }
                    }
                }

                install(ContentNegotiation) {
                    gson {
                        setPrettyPrinting()
                    }
                }

                // Logging
                install(Logging) {
                    logger =
                        object : Logger {
                            override fun log(message: String) {
                                Log.d("HttpLogging:", message)
                            }
                        }
                    level = LogLevel.ALL
                }

                // Http Response
                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status:", "${response.status.value}")
                    }
                }

                // Headers
                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
            }
        }
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
