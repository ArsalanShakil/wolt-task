package com.example.wolttask.ui.deliveryfee


import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wolttask.ui.DatePickerFragment
import com.example.wolttask.R
import com.example.wolttask.ui.TimePickerFragment
import com.example.wolttask.databinding.DeliveryFeeCalculatorFragmentBinding
import kotlinx.android.synthetic.main.delivery_fee_calculator_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DeliveryFeeCalculatorFragment : Fragment() {
    private val viewModel: DeliveryFeeCalculatorViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: DeliveryFeeCalculatorFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.delivery_fee_calculator_fragment, container, false
        )
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateTimeEt.setOnClickListener {
            showTimePickerDialog()
            showDatePickerDialog()

        }

        calculateDeliveryPriceBtn.setOnClickListener {
            viewModel.calculateFee()
            val deliveryFees = String.format(
                getString(R.string.delivery_price_value),
                viewModel.totalFee
            )
            deliveryPriceTv.text = deliveryFees
        }

        viewModel.dateString.observe(viewLifecycleOwner, {
            dateTimeEt.setText(it)
        })

        viewModel.isEnabled.observe(viewLifecycleOwner, {
            calculateDeliveryPriceBtn.isEnabled = it
            setStyle(it)

        })

    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val newFragment = TimePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "timePicker")
    }

    private fun setStyle(isEnabled: Boolean) {
        if (isEnabled) {
            calculateDeliveryPriceBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.wolt_dark_blue))
            deliveryPriceTv.setTypeface(null, Typeface.BOLD)
            deliveryPriceTv.text = getString(R.string.delivery_price)
        } else {
            calculateDeliveryPriceBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.wolt_dark_grey))
            deliveryPriceTv.setTypeface(null, Typeface.NORMAL)
            deliveryPriceTv.text = getString(R.string.please_fill_all_fields)
        }
    }

}


