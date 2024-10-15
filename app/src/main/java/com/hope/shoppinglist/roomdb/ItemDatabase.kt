package com.hope.shoppinglist.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hope.shoppinglist.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun ItemDao() : ItemDao

    companion object{
        @Volatile
        private var INSTANCE : ItemDatabase? = null
        private var lock = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock){
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ItemDatabase::class.java,
            "ItemDatabase"
        ).build()
    }
}