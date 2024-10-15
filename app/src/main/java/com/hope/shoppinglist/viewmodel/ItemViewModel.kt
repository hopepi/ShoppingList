package com.hope.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hope.shoppinglist.model.Item
import com.hope.shoppinglist.roomdb.ItemDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val database: ItemDatabase = ItemDatabase(application)
    private val _itemList = MutableStateFlow<List<Item>>(listOf())
    private val _selectedItem = MutableStateFlow<Item>(Item("","","",ByteArray(1)))
    val itemList = _itemList.asStateFlow()
    val selectedItem = _selectedItem.asStateFlow()

    init {
        getItemList()
    }

    fun updateItemList(newItems: List<Item>) {
        _itemList.value = newItems
    }

    fun updateselectedItem(item: Item) {
        _selectedItem.value = item
    }

    fun getItemList(){
        viewModelScope.launch (Dispatchers.IO){
            _itemList.value = database.ItemDao().getItemWithNameAndId()
        }
    }

    fun getItem(id : Int){
        viewModelScope.launch (Dispatchers.IO){
            val item = database.ItemDao().getItemById(id)
            item?.let {
                _selectedItem.value = it
            }
        }
    }

    fun saveItem(item: Item)
    {
        viewModelScope.launch(Dispatchers.IO) {
            database.ItemDao().insertItem(item)
        }
    }

    fun deleteItem(item: Item)
    {
        viewModelScope.launch(Dispatchers.IO) {
            database.ItemDao().deleteItem(item)
        }
    }
}