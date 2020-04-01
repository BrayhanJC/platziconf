package com.uniquindio.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uniquindio.platziconf.model.Speaker
import com.uniquindio.platziconf.network.Callback
import com.uniquindio.platziconf.network.FirestoreService

class SpeakerViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    private fun getSpeakerFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                //super.onSuccess(result)
                listSpeaker.postValue(result)
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