package com.uniquindio.platziconf.view.adapter
import com.uniquindio.platziconf.model.Speaker

interface SpeakerListener {

    fun onSpeakerClicked(speaker: Speaker, position: Int)
}