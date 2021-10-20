/*
 * Copyright (c) 2021 by Arsalan Shakil.
 * Wolt task project.
 *
 */

package com.example.wolttask.helpers

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

    fun travelDistanceFeeCalculator(distanceInMeters: Int) =
        2f.let { distanceSum ->
            (distanceInMeters - 1000).let { remainingDistance ->
                if (remainingDistance > 0) {
                    distanceSum + ceil(remainingDistance / 500f)
                } else {
                    distanceSum
                }
            }
        }

    fun deliveryFeeNormalization(deliveryFee: Float) = deliveryFee.coerceIn(0f, 15f)

    fun cartValueHundredOrGreater(cartValue: Float) = cartValue >= 100

    fun rushHourMultiplier(isRushHours: Boolean, totalFees: Float) =
        if (isRushHours) totalFees * 1.1f else totalFees

    fun isRushHour(
        calendar: Calendar,
        currentTimeHours: Int,
        currentTimeMinutes: Int,
        currentTimeSeconds: Int
    ) =
        (currentTimeHours in 15..18 && calendar.get(Calendar.DAY_OF_WEEK) == 6) || (currentTimeHours == 19 && currentTimeMinutes == 0 && currentTimeSeconds == 0)


    fun totalDeliveryFeeCalculator(
        cartValue: Float,
        deliveryDistance: Int,
        amountOfItems: Int,
        time: Calendar
    ) = if (cartValueHundredOrGreater(cartValue)) {
            0f
        } else {
            (getCartValueSurcharge(cartValue) + getAmountOfItemsSurcharge(amountOfItems) + travelDistanceFeeCalculator(
                deliveryDistance
            )).let {
                deliveryFeeNormalization(
                    rushHourMultiplier(
                        isRushHour(
                            time,
                            time[Calendar.HOUR_OF_DAY],
                            time[Calendar.MINUTE],
                            time[Calendar.SECOND]
                        ), it
                    )
                )
            }
        }
}
