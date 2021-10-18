package com.example.wolttask.ui.deliveryfee


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wolttask.DatePickerFragment
import com.example.wolttask.R
import com.example.wolttask.databinding.DeliveryFeeCalculatorFragmentBinding
import kotlinx.android.synthetic.main.delivery_fee_calculator_fragment.*


class DeliveryFeeCalculatorFragment : Fragment() {
    private val viewModel: DeliveryFeeCalculatorViewModel by activityViewModels()

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

        timeEt.setOnClickListener {
            showDatePickerDialog(view)
        }

        calculateDevliveryPriceBtn.setOnClickListener {
            viewModel.calculateFee()
            val deliveryFees = String.format(
                getString(R.string.delivery_price_value),
                viewModel.totalFee
            )
            deliveryPriceTv.text = deliveryFees
        }

        viewModel.dateString.observe(viewLifecycleOwner, {
            timeEt.setText(it)
        })

        viewModel.isEnabled.observe(viewLifecycleOwner, {
            calculateDevliveryPriceBtn.isEnabled = it
        })

    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

}


