package uz.alphadroid.mycompose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.alphadroid.mycompose.data.MainDB
import uz.alphadroid.mycompose.data.NameEntity

class MainViewModel : ViewModel() {

    private val dao = MainDB.getInstance().dao()
    val itemsList = dao.getAllItems()
    val newText = mutableStateOf("")
    var nameEntity:NameEntity?=null

    fun insertItem ()= viewModelScope.launch {
        val nameItem=nameEntity?.copy(name = newText.value)?: NameEntity(name = newText.value)
        dao.insertItem(nameItem)
        nameEntity=null
        newText.value=""
    }

    fun deleteItem(item:NameEntity)=viewModelScope.launch {
        dao.deleteItem(item)
    }

}