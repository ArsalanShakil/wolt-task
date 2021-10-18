package com.example.wolttask.ui.deliveryfee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DeliveryFeeCalculatorViewModel : ViewModel() {

    val selectedDateHourMinute: MutableLiveData<Calendar> = MutableLiveData(Calendar.getInstance())
    val dateTimeObserver: MutableLiveData<Int> = MutableLiveData(0)

    fun selectHourMinute(hour: Int, minute:Int) {

        selectedDateHourMinute.value?.set(Calendar.HOUR_OF_DAY, hour)
        selectedDateHourMinute.value?.set(Calendar.MINUTE, minute)
        dateTimeObserver.value = dateTimeObserver.value?.plus(1)

    }

    fun selectDate(day:Int,month:Int,year:Int) {
        selectedDateHourMinute.value?.set(Calendar.DAY_OF_MONTH, day)
        selectedDateHourMinute.value?.set(Calendar.MONTH, month)
        selectedDateHourMinute.value?.set(Calendar.YEAR, year)
        dateTimeObserver.value = dateTimeObserver.value?.plus(1)
    }
}