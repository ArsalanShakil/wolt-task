package com.example.wolttask

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.wolttask.ui.deliveryfee.DeliveryFeeCalculatorViewModel
import java.util.*

class DatePickerHelper : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val viewModel: DeliveryFeeCalculatorViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        c.add(Calendar.DATE, 0) // Add 0 days to Calendar

        // Create a new instance of DatePickerDialog and return ii
        val datePickerDialog = DatePickerDialog(requireActivity(), this, year, month, day)
        val newDate = c.time
        datePickerDialog.datePicker.minDate = newDate.time - newDate.time % (24 * 60 * 60 * 1000)
        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        viewModel.selectDate(day,month,year)
        showTimePickerDialog(view)

    }
    private fun showTimePickerDialog(v: View) {
        val newFragment = TimePickerHelper()
        newFragment.show(requireActivity().supportFragmentManager, "timePicker")
    }
}

