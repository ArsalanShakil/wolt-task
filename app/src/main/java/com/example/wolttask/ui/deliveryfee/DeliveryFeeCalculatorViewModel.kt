package com.example.wolttask.ui.deliveryfee

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wolttask.DeliveryCostHelper
import java.text.SimpleDateFormat
import java.util.*

class DeliveryFeeCalculatorViewModel : ViewModel() {
    val cartValue = MutableLiveData("")
    val deliveryDistance = MutableLiveData("")
    val amountOfItems = MutableLiveData("")
    val isEnabled = MediatorLiveData<Boolean>()
    var totalFee: Float = 0f
    private val deliveryCostHelper = DeliveryCostHelper()
    private val date = Calendar.getInstance()
    var dateString = MutableLiveData("")
    private val dateFormatter = SimpleDateFormat("dd LLLL yyyy, HH:mm", Locale.getDefault())

    init {
        isEnabled.run {
            addSource(cartValue) { cartValue ->
                isEnabled.value =
                    getEnabled(cartValue, deliveryDistance.value!!, amountOfItems.value!!, dateString.value!!)
            }

            addSource(deliveryDistance) { deliveryDistance ->
                isEnabled.value =
                    getEnabled(cartValue.value!!, deliveryDistance, amountOfItems.value!!, dateString.value!!)
            }

            addSource(amountOfItems) { amountOfItems ->
                isEnabled.value =
                    getEnabled(cartValue.value!!, deliveryDistance.value!!, amountOfItems, dateString.value!!)
            }

            addSource(dateString) { dateString ->
                isEnabled.value =
                    getEnabled(cartValue.value!!, deliveryDistance.value!!, amountOfItems.value!!, dateString)
            }
        }
    }

    private fun getEnabled(
        cartValue: String,
        deliveryDistance: String,
        amountOfItems: String,
        date: String
    ) = if (cartValue.isNotBlank() && cartValue != "." && deliveryDistance.isNotBlank() && amountOfItems.isNotBlank() && date.isNotBlank()){
        (cartValue.toFloat().toInt() != 0) &&
            (cartValue.toFloat() != 0f) &&
            (deliveryDistance.toFloat().toInt() != 0) &&
            (deliveryDistance.toFloat() != 0f) &&
            (amountOfItems.toFloat().toInt() != 0) &&
            (amountOfItems.toFloat() != 0f)
    } else{

        false
    }





    fun calculateFee() {
        totalFee =
            deliveryCostHelper.totalDeliveryFeeCalculator(
                cartValue.value!!.toFloat(),
                deliveryDistance.value!!.toInt(),
                amountOfItems.value!!.toInt(),
                date
            )
    }

    fun selectHourMinute(hour: Int, minute: Int) {
        date.set(Calendar.HOUR_OF_DAY, hour)
        date.set(Calendar.MINUTE, minute)
        date.set(Calendar.SECOND, 0)
        dateString.value = dateFormatter.format(date.time)
    }

    fun selectDate(day: Int, month: Int, year: Int) {
        date.set(Calendar.DAY_OF_MONTH, day)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.YEAR, year)
        dateString.value = dateFormatter.format(date.time)
    }
}