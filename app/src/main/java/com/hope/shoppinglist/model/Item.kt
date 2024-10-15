package com.hope.shoppinglist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Item")
class Item(
    var itemName : String,
    var storeName : String?,
    var price : String?,
    var image : ByteArray?
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}