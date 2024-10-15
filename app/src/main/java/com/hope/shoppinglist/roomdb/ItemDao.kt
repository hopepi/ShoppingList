package com.hope.shoppinglist.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hope.shoppinglist.model.Item

@Dao
interface ItemDao {

    @Query("SELECT itemName,id FROM Item")
    fun getItemWithNameAndId() : List<Item>

    @Query("SELECT * FROM Item WHERE id =:id")
    fun getItemById(id : Int) : Item?

    @Insert
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}