package priya.pradipta.store.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Query("SELECT * FROM cartentity")
    fun getAllCarts(): List<CartEntity>

    @Insert
    fun saveToCarts(cartEntity: CartEntity)
}
