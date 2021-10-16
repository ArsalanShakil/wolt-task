package com.example.wolttask

import org.junit.Assert
import org.junit.Test

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
        var amountOfItem = 4f
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 0f)

        // amount of items is greater than 5
        amountOfItem = 10f
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 3f)

        // amount of items is equal to 5
        amountOfItem = 5f
        Assert.assertEquals(deliveryCostHelper.getAmountOfItemsSurcharge(amountOfItem), 0.5f)

    }

    @Test
    fun travelDistanceFeeCalculatorTest() {

    /*   If the delivery distance is 1499 meters, the delivery fee is: 2€
         base fee + 1€ for the additional 500 m => 3€
          */
        var distance = 1499f
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 3f)

        // If the delivery distance is 1500 meters, the delivery fee is: 2€
        //base fee + 1€ for the additional 500 m => 3€
        distance = 1500f
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 3f)

        // If the delivery distance is 1501 meters, the delivery fee is: 2€
        //base fee + 1€ for the first 500 m + 1€ for the second 500 m => 4€
        distance = 1501f
        Assert.assertEquals(deliveryCostHelper.travelDistanceFeeCalculator(distance), 4f)

        distance = 800f
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
    fun isRushHourTest() {

        Assert.assertTrue(deliveryCostHelper.isRushHour(16,0,0))

        Assert.assertTrue(deliveryCostHelper.isRushHour(15,0,0))

        Assert.assertTrue(deliveryCostHelper.isRushHour(19,0,0))

        Assert.assertFalse(deliveryCostHelper.isRushHour(13,0,0))

        Assert.assertFalse(deliveryCostHelper.isRushHour(19,1,0))

    }




}