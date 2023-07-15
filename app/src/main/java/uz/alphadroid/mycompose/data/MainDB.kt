package uz.alphadroid.mycompose.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameEntity::class], version = 1)
abstract class MainDB : RoomDatabase() {

    abstract fun dao(): NameDao

    companion object {
        private lateinit var instance: MainDB
        fun init(ctx: Context) {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(ctx, MainDB::class.java, "names.db")
                    .build()
            }
        }
        fun getInstance() = instance
    }
}