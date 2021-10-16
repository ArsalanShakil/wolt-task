package com.example.wolttask

import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.math.ceil

class DeliveryCostHelper {

    fun getCartValueSurcharge(cartValue: Float) = when {
        cartValue < 10 -> 10 - cartValue
        else -> 0f
    }

    fun getAmountOfItemsSurcharge(amountOfItems: Int) = when {
        amountOfItems >= 5 -> (amountOfItems - 4) * 0.5f
        else -> 0f
    }

    fun travelDistanceFeeCalculator(distanceInMeters: Float) =
        2f.let { distanceSum ->
            (distanceInMeters - 1000).let { remainingDistance ->
                if (remainingDistance > 0) {
                    distanceSum + ceil(remainingDistance / 500)
                } else {
                    distanceSum
                }
            }
        }

    fun deliveryFeeNormalization(deliveryFee: Float) = deliveryFee.coerceIn(0f, 15f)

    fun cartValueHundredOrGreater(cartValue: Float) =
        if (cartValue >= 100) 0f else cartValue

    fun rushHourMultiplier(isRushHours:Boolean, totalFees: Float) = if (isRushHours) totalFees * 1.1f else totalFees

    fun isRushHour(calendar: Calendar,currentTimeHours: Int, currentTimeMinutes: Int,currentTimeSeconds: Int) = (currentTimeHours in 15..18 && calendar.get(Calendar.DAY_OF_WEEK) == 5) || (currentTimeHours == 19 && currentTimeMinutes == 0 && currentTimeSeconds == 0)


    fun totalDeliveryFeeCalculator(cartValue: Float, deliveryDistance: Float, amountOfItems: Int, time:Calendar)  =
        if (cartValueHundredOrGreater(cartValue) == 0f) {
            0f
        } else {
            val totalDeliveryFees = getCartValueSurcharge(cartValue) + getAmountOfItemsSurcharge(amountOfItems) + travelDistanceFeeCalculator(deliveryDistance)
            when(isRushHour(time,time[Calendar.HOUR],time[Calendar.MINUTE],time[Calendar.SECOND])){

                true -> deliveryFeeNormalization(rushHourMultiplier(true,totalDeliveryFees))
                false -> deliveryFeeNormalization(totalDeliveryFees)
            }
        }













  /*  fun isRushHour(calendar: Calendar): Boolean {
        val startHour = Calendar.getInstance()
        startHour[Calendar.HOUR] = 15
        startHour[Calendar.MINUTE] = 0
        startHour[Calendar.SECOND] = 0
        startHour[Calendar.MILLISECOND] = 0

        val endHour = Calendar.getInstance()
        endHour[Calendar.HOUR] = 19
        endHour[Calendar.MINUTE] = 0
        endHour[Calendar.SECOND] = 0
        endHour[Calendar.MILLISECOND] = 0

        return !(calendar.before(startHour) || calendar.after(endHour))
    }*/

}
