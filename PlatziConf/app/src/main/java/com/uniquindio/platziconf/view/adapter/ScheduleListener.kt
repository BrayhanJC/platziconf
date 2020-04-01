package com.uniquindio.platziconf.view.adapter

import com.uniquindio.platziconf.model.Conference

interface ScheduleListener  {

    fun onConferenceClicked(conference: Conference, position: Int)
}