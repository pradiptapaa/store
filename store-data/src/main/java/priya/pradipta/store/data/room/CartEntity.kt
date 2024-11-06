package priya.pradipta.store.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "image")
    val image: String? = null,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "price")
    val price: String = "",
    @ColumnInfo(name = "quantity")
    val quantity: String = "",
)
