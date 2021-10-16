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

    fun getAmountOfItemsSurcharge(amountOfItems: Float) = when {
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

    fun cartValueGreaterThan100(cartValue: Float) =
        if (getCartValueSurcharge(cartValue) >= 100) 0f else cartValue

    fun rushHourMultiplier(isRushHours:Boolean, totalFees: Float) = if (isRushHours) totalFees * 1.1f else totalFees

    fun isRushHour(currentTimeHours: Int, currentTimeMinutes: Int,currentTimeSeconds: Int) = (currentTimeHours >= 15 && currentTimeHours <= 18) || (currentTimeHours == 19 && currentTimeMinutes == 0 && currentTimeSeconds == 0)

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
