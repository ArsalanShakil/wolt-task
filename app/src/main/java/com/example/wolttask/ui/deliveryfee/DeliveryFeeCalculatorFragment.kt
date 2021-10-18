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
    var date = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_fee_calculator_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<EditText>(R.id.timeEt).setOnClickListener {
            showDatePickerDialog(view)

        }


        val cartValue = view.findViewById<EditText>(R.id.cartValueEt)?.text?.toString() ?: " "
        val deliveryDistance =
            view.findViewById<EditText>(R.id.deliveryDistanceEt)?.text?.toString() ?: " "
        val amountOfItems =
            view.findViewById<EditText>(R.id.amountOfItemsEt)?.text?.toString() ?: " "


        viewModel.selectedDateHourMinute.observe(viewLifecycleOwner, {
            date = it
            val s = date[Calendar.DAY_OF_MONTH].toString()
            //the date is not updating when the user pickes the date
            view.findViewById<EditText>(R.id.timeEt).setText(s)
            //once that is done show the time and date in edit text
        })



        view.findViewById<Button>(R.id.calculateDevliveryPriceBtn).setOnClickListener {
            Log.d(
                "dateTime button",
                "${date[Calendar.DAY_OF_MONTH]}-${date[Calendar.MONTH]}-${date[Calendar.YEAR]}, ${date[Calendar.DAY_OF_WEEK]} || ${date[Calendar.HOUR_OF_DAY]}:${date[Calendar.MINUTE]}"
            )
            if (cartValue.isNullOrBlank() || deliveryDistance.isNullOrBlank() || amountOfItems.isNullOrBlank()) {
                view.findViewById<TextView>(R.id.deliveryPriceTv).text = getString(R.string.please_fill_all_fields)
            } else {
                val totalFee =
                    deliveryCostHelper.totalDeliveryFeeCalculator(
                        cartValue.toFloat(),
                        deliveryDistance.toInt(),
                        amountOfItems.toInt(),
                        date
                    )
                view.findViewById<TextView>(R.id.deliveryPriceTv).text =
                    resources.getString(R.string.delivery_price_value, totalFee.toString())
            }
        }


    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerHelper()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }


}


