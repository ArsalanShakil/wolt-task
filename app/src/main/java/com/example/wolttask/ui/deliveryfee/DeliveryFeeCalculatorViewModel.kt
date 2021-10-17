package com.example.wolttask.ui.deliveryfee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DeliveryFeeCalculatorViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val selectedDateHourMinute: MutableLiveData<Calendar> = MutableLiveData(Calendar.getInstance())

    fun selectHourMinute(hour: Int, minute:Int) {

        selectedDateHourMinute.value?.set(Calendar.HOUR_OF_DAY, hour)
        selectedDateHourMinute.value?.set(Calendar.MINUTE, minute)
    }

    fun selectDate(day:Int,month:Int,year:Int) {
        selectedDateHourMinute.value?.set(Calendar.DAY_OF_MONTH, day)
        selectedDateHourMinute.value?.set(Calendar.MONTH, month)
        selectedDateHourMinute.value?.set(Calendar.YEAR, year)
    }
}