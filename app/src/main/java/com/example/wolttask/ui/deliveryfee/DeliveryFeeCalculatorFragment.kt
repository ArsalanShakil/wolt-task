package com.example.wolttask.ui.deliveryfee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


import androidx.fragment.app.activityViewModels
import com.example.wolttask.DatePickerHelper
import com.example.wolttask.DeliveryCostHelper
import com.example.wolttask.R
import com.example.wolttask.TimePickerHelper
import java.util.*


class DeliveryFeeCalculatorFragment : Fragment() {

    private val deliveryCostHelper = DeliveryCostHelper()


    private val viewModel: DeliveryFeeCalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_fee_calculator_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<EditText>(R.id.timeEt).setOnClickListener {
            showTimePickerDialog(view)
            showDatePickerDialog(view)
        }

        view.findViewById<Button>(R.id.calculateDevliveryPriceBtn).setOnClickListener {
            val cartValue = view.findViewById<EditText>(R.id.cartValueEt)?.text?.toString() ?: "null"
            val deliveryDistance = view.findViewById<EditText>(R.id.deliveryDistanceEt)?.text?.toString() ?: "null"
            val amountOfItems = view.findViewById<EditText>(R.id.amountOfItemsEt)?.text?.toString() ?: "null"

            viewModel.selectedDateHourMinute.observe(viewLifecycleOwner,{
                Log.d("dateTime","${it[Calendar.DAY_OF_MONTH]}-${it[Calendar.MONTH]}-${it[Calendar.YEAR]}, ${it[Calendar.DAY_OF_WEEK]} || ${it[Calendar.HOUR_OF_DAY]}:${it[Calendar.MINUTE]}")

                val totalFee =
                    deliveryCostHelper.totalDeliveryFeeCalculator(cartValue.toFloat(),deliveryDistance.toInt(),amountOfItems.toInt(),it)

                view.findViewById<TextView>(R.id.deliveryPriceTv).text = totalFee.toString()

            })




        }


    }
    private fun showTimePickerDialog(v: View) {
        val newFragment = TimePickerHelper()
        newFragment.show(requireActivity().supportFragmentManager, "timePicker")
    }
    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerHelper()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }





}


