package com.uniquindio.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uniquindio.platziconf.model.Conference
import com.uniquindio.platziconf.network.Callback
import com.uniquindio.platziconf.network.FirestoreService

class ScheduleViewModel: ViewModel(){
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                //super.onSuccess(result)
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                //super.onFailled(exception)
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true

    }
}