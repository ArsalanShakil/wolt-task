package com.example.wolttask.ui.deliveryfee

import com.example.wolttask.helpers.DeliveryCostHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import com.google.common.truth.Truth.assertThat
import java.text.SimpleDateFormat
import java.util.*

class DeliveryFeeCalculatorViewModelTest {
    private lateinit var viewModel : DeliveryFeeCalculatorViewModel
    private lateinit var deliveryCostHelper: DeliveryCostHelper


    @Before
    fun setUp() {
        deliveryCostHelper = Mockito.mock(DeliveryCostHelper::class.java)
        viewModel = DeliveryFeeCalculatorViewModel(deliveryCostHelper)
    }
    @Test
    fun getEnabledTest() {

        //When user inputs . in cart value
        Assert.assertFalse(viewModel.getEnabled(".","1000","4","20 November 2021, 15:15"))

        //When user inputs 0 in cart value
        Assert.assertFalse(viewModel.getEnabled("0","1000","4","20 November 2021, 15:15"))

        //When user inputs 0.0 in cart value
        Assert.assertFalse(viewModel.getEnabled("0.0","1000","4","20 November 2021, 15:15"))

        //When user inputs . in cart value, delivery distance 0 and amount of items 0
        Assert.assertFalse(viewModel.getEnabled(".","0","0","20 November 2021, 15:15"))

        //When user inputs 0 in cart value, delivery distance 0 and amount of items 0
        Assert.assertFalse(viewModel.getEnabled("0","0","0","20 November 2021, 15:15"))

        //When user inputs 0.0 in cart value, delivery distance 0 and amount of items 0
        Assert.assertFalse(viewModel.getEnabled("0.0","0","0","20 November 2021, 15:15"))

        //When user inputs 9 in cart value, delivery distance 0 and amount of items 4
        Assert.assertFalse(viewModel.getEnabled("9","0","4","20 November 2021, 15:15"))

        //When user inputs 9 in cart value, delivery distance 400 and amount of items 0
        Assert.assertFalse(viewModel.getEnabled("9","400","0","20 November 2021, 15:15"))

        //When user inputs 9 in cart value, delivery distance 400 and amount of items 9 and Date is blank
        Assert.assertFalse(viewModel.getEnabled("9","400","9",""))

        //When user inputs 9 in cart value, delivery distance 400 and amount of items 4
        Assert.assertTrue(viewModel.getEnabled("9","400","4","20 November 2021, 15:15"))

    }

 /*   @Test
    fun selectHourMinuteTest() {
        val time = Calendar.getInstance()
        time[Calendar.DAY_OF_WEEK] = 4
        time[Calendar.HOUR_OF_DAY] = 15
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0
        time[Calendar.DAY_OF_MONTH] = 16
        time[Calendar.MONTH] = 12
        time[Calendar.YEAR] = 2021

        val dateFormatter = SimpleDateFormat("dd LLLL yyyy, HH:mm", Locale.getDefault())


        //When user inputs 16 in hour and 30 in minutes
        viewModel.selectDate(time[Calendar.DAY_OF_MONTH],time[Calendar.MONTH],time[Calendar.YEAR])
        viewModel.selectHourMinute(time[Calendar.HOUR_OF_DAY],time[Calendar.MINUTE])
        val result = viewModel.dateString.getOrAwaitValueTest()

    }*/


}