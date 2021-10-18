package com.example.wolttask.ui.deliveryfee


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wolttask.DatePickerHelper
import com.example.wolttask.DeliveryCostHelper
import com.example.wolttask.R
import java.text.SimpleDateFormat
import java.util.*


class DeliveryFeeCalculatorFragment : Fragment() {

    private val deliveryCostHelper = DeliveryCostHelper()


    private val viewModel: DeliveryFeeCalculatorViewModel by activityViewModels()
    private var date: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_fee_calculator_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<EditText>(R.id.timeEt).setOnClickListener {
            showDatePickerDialog()

        }



        viewModel.selectedDateHourMinute.observe(viewLifecycleOwner, {
            date = it
            val setValueDateTime = date[Calendar.DAY_OF_MONTH].toString()
            view.findViewById<EditText>(R.id.timeEt).setText(setValueDateTime)
        })

        viewModel.dateTimeObserver.observe(viewLifecycleOwner, {
            Log.d("observer", it.toString())
            val simpleDateFormat = SimpleDateFormat("dd LLLL yyyy, HH:mm",Locale.getDefault())
                val s = simpleDateFormat.format(date.time)
            //the date is not updating when the user picks the date
            view.findViewById<EditText>(R.id.timeEt).setText(s)
            //once that is done show the time and date in edit text
        })



        view.findViewById<Button>(R.id.calculateDevliveryPriceBtn).setOnClickListener {
            val cartValue = view.findViewById<EditText>(R.id.cartValueEt).text.toString()
            val deliveryDistance =
                view.findViewById<EditText>(R.id.deliveryDistanceEt).text.toString()
            val amountOfItems =
                view.findViewById<EditText>(R.id.amountOfItemsEt).text.toString()

            Log.d(
                "dateTime button",
                "${date[Calendar.DAY_OF_MONTH]}-${date[Calendar.MONTH]}-${date[Calendar.YEAR]}, ${date[Calendar.DAY_OF_WEEK]} || ${date[Calendar.HOUR_OF_DAY]}:${date[Calendar.MINUTE]}"
            )
            if (cartValue.isBlank() || deliveryDistance.isBlank() || amountOfItems.isBlank()) {
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
                    resources.getString(R.string.delivery_price_value, totalFee)
            }
        }


    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerHelper()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }


}


