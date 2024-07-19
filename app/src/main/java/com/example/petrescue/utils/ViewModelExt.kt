package com.example.petrescue.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T> ViewModel.launchFromNetwork(
    liveData: MutableLiveData<Resource<T>>,
    call: suspend () -> T
){
    viewModelScope.launch {
         runCatching {
             call.invoke()
         }.onSuccess {
             liveData.value = Resource.success(it)
         }.onFailure {
             val error = Resource.failure<T>(it)
             {this@launchFromNetwork.launchFromNetwork(liveData,call)}
             liveData.value = error
         }
    }
}