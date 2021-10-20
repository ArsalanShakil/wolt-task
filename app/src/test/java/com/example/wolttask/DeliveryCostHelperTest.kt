/*
 * Copyright (c) 2021 by Arsalan Shakil.
 * Wolt task project.
 *
 */

package com.example.wolttask

import com.example.wolttask.helpers.DeliveryCostHelper
import org.junit.Assert
import org.junit.Test
import java.util.*

class DeliveryCostHelperTest {
    private val deliveryCostHelper = DeliveryCostHelper()

    @Test
    fun getCartValueSurchargeTest() {

        // cart value is exactly 10e
        var cartValue = 10f
        Assert.assertEquals(deliveryCostHelper.getCartValueSurcharge(cartValue = cartValue), 0f)

        // cart value is less than 10
        cartValue = 8f
        Assert.assertEquals(deliveryCostHelper.getCartValueSurcharge(cartValue = cartValue), 2f)

        // cart value is less than 10
        cartValue = 15f
        Assert.assertEquals(deliveryCostHelper.getCartValueSurcharge(cartValue = cartValue), 0f)

    }
    @Test
    fun getAmountOfItemsSurchargeTest() {

        // amount of items is exactly 4
        var amountOfItem = 4
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 0f)

        // amount of items is greater than 5
        amountOfItem = 10
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 3f)

        // amount of items is equal to 5
        amountOfItem = 5
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 0.5f)

    }

    @Test
    fun travelDistanceFeeCalculatorTest() {

    /*   If the delivery distance is 1499 meters, the delivery fee is: 2€
         base fee + 1€ for the additional 500 m => 3€
          */
        var distance = 1499
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 3f)

        // If the delivery distance is 1500 meters, the delivery fee is: 2€
        //base fee + 1€ for the additional 500 m => 3€
        distance = 1500
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 3f)

        // If the delivery distance is 1501 meters, the delivery fee is: 2€
        //base fee + 1€ for the first 500 m + 1€ for the second 500 m => 4€
        distance = 1501
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 4f)

        // If the delivery distance is 800 meters, the delivery fee is: 2€
        //total delivery fee => 2€
        distance = 800
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 2f)

    }

    @Test
    fun deliveryFeeNormalizationTest() {

        // delivery fee is 10€
        var deliveryFee = 10f
        Assert.assertEquals(deliveryCostHelper.deliveryFeeNormalization(deliveryFee), 10f)

        // delivery fee is 15€
        deliveryFee = 15f
        Assert.assertEquals(deliveryCostHelper.deliveryFeeNormalization(deliveryFee), 15f)

        // delivery fee is 18€
        deliveryFee = 18f
        Assert.assertEquals(deliveryCostHelper.deliveryFeeNormalization(deliveryFee), 15f)

        // delivery fee is 0€
        deliveryFee = 0f
        Assert.assertEquals(deliveryCostHelper.deliveryFeeNormalization(deliveryFee), 0f)

        // delivery fee is -18€
        deliveryFee = -18f
        Assert.assertEquals(deliveryCostHelper.deliveryFeeNormalization(deliveryFee), 0f)

    }

    @Test
    fun cartValueHundredOrGreaterTest() {

        // cart value is 50€
        var cartValue = 50f
        Assert.assertFalse(deliveryCostHelper.cartValueHundredOrGreater(cartValue))

        // cart value is 100€
        cartValue = 100f
        Assert.assertTrue(deliveryCostHelper.cartValueHundredOrGreater(cartValue))

        // cart value is 18€
        cartValue = 150f
        Assert.assertTrue(deliveryCostHelper.cartValueHundredOrGreater(cartValue))

    }

    @Test
    fun rushHourMultiplierTest() {

        // is rush hour = true and total fees is 50€
        Assert.assertEquals(deliveryCostHelper.rushHourMultiplier(true,50f), 55f)

        // is rush hour = false and total fees is 50€
        Assert.assertEquals(deliveryCostHelper.rushHourMultiplier(false, 50f), 50f)

        // is rush hour = false and total fees is 10€
        Assert.assertEquals(deliveryCostHelper.rushHourMultiplier(true,10f), 11f)

    }



    @Test
    fun isRushHourTest() {

        // if the time is 16 o'clock and Tuesday
        val dayOfWeek = Calendar.getInstance()
        dayOfWeek[Calendar.DAY_OF_WEEK] = 3
        Assert.assertFalse(deliveryCostHelper.isRushHour(dayOfWeek,16,0,0))

        // if the time is 15 o'clock and Sunday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 1
        Assert.assertFalse(deliveryCostHelper.isRushHour(dayOfWeek,15,0,0))

        // if the time is 19 o'clock and Friday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 6
        Assert.assertTrue(deliveryCostHelper.isRushHour(dayOfWeek,19,0,0))

        // if the time is 16 o'clock and Friday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 6
        Assert.assertTrue(deliveryCostHelper.isRushHour(dayOfWeek,16,0,0))

        // if the time is 15 o'clock and Friday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 6
        Assert.assertTrue(deliveryCostHelper.isRushHour(dayOfWeek,15,0,0))

        // if the time is 13 o'clock and Saturday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 7
        Assert.assertFalse(deliveryCostHelper.isRushHour(dayOfWeek,13,0,0))

        // if the time is 19:01 o'clock and Friday
        dayOfWeek[Calendar.DAY_OF_WEEK] = 6
        Assert.assertFalse(deliveryCostHelper.isRushHour(dayOfWeek,19,1,0))

    }

    @Test
    fun totalDeliveryFeeCalculatorTest() {

        /* when cart value is 100€
        delivery distance is 1000m
        amount of items is 4
        time is Wednesday 15 o'clock
         */
        val time = Calendar.getInstance()
        time[Calendar.DAY_OF_WEEK] = 4
        time[Calendar.HOUR_OF_DAY] = 15
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0

        Assert.assertEquals(deliveryCostHelper.totalDeliveryFeeCalculator(100f,1000,4, time), 0f)


        /* when cart value is 120€
        delivery distance is 1500m
        amount of items is 8
        time is Friday 16 o'clock
         */
        time[Calendar.DAY_OF_WEEK] = 6
        time[Calendar.HOUR_OF_DAY] = 16
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0

        Assert.assertEquals(deliveryCostHelper.totalDeliveryFeeCalculator(120f,1500,8, time), 0f)



        /* when cart value is 15€
        delivery distance is 500m
        amount of items is 4
        time is Monday 16 o'clock
         */
        time[Calendar.DAY_OF_WEEK] = 2
        time[Calendar.HOUR_OF_DAY] = 16
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0

        Assert.assertEquals(deliveryCostHelper.totalDeliveryFeeCalculator(15f,500,4, time), 2f)


        /* when cart value is 1€
        delivery distance is 9500m
        amount of items is 35
        time is Friday 18 o'clock
         */
        time[Calendar.DAY_OF_WEEK] = 6
        time[Calendar.HOUR_OF_DAY] = 18
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0

        Assert.assertEquals(deliveryCostHelper.totalDeliveryFeeCalculator(1f,9500,35, time), 15f)


        /* when cart value is 1€
        delivery distance is 1500m
        amount of items is 4
        time is Saturday 18 o'clock
        */
        time[Calendar.DAY_OF_WEEK] = 7
        time[Calendar.HOUR_OF_DAY] = 18
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0

        Assert.assertEquals(deliveryCostHelper.totalDeliveryFeeCalculator(1f,1500,4, time), 12f)



    }




}