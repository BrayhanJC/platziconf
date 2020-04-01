package com.uniquindio.platziconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.uniquindio.platziconf.model.Conference
import com.uniquindio.platziconf.model.Speaker

const val CONFERENCE_COLLECTION_NAME = "conference"
const val SPEAKER_COLLECTION_NAME = "speaker"
class FirestoreService {

    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
    firebaseFirestore.collection(SPEAKER_COLLECTION_NAME)
        .orderBy("category")
        .get()
        .addOnSuccessListener { result ->
            for (doc in result){
                val list = result.toObjects(Speaker::class.java)
                callback.onSuccess(list)
                break
            }
        }
    }

    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCE_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}