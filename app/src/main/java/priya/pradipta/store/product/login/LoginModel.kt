package priya.pradipta.store.product.login

import android.app.admin.TargetUser
import kotlinx.serialization.Serializable

@Serializable
object LoginModel

@Serializable
data class LoginParameter(
    val username: String = "",
    val password: String = "",
)
