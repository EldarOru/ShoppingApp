package com.example.shoppingapp.presentation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.data.ShopListRepositoryImpl
import com.example.shoppingapp.domain.AddShopItemUseCase
import com.example.shoppingapp.domain.ChangeShopItemUseCase
import com.example.shoppingapp.domain.GetShopItemUseCase
import com.example.shoppingapp.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemUserCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val changeShopItemUseCase = ChangeShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int){
        val item = getShopItemUserCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if(fieldValid) {
            val shopItem = ShopItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun changeShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if(fieldValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                changeShopItemUseCase.changeShopItem(item)
                finishWork()
            }

        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e:Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean{
        var flag = true
        if (name.isBlank()){
            _errorInputName.value = true
            flag = false
        }
        if (count <= 0){
            _errorInputCount.value = true
            flag = false
        }
        return flag
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }

    fun resetErrorInputCount(){
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}