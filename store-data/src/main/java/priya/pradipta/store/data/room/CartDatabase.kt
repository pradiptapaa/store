package priya.pradipta.store.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): CartDao
}
