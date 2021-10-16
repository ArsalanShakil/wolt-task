package com.example.wolttask

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DeliveryFeeCalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = DeliveryFeeCalculatorFragment()
    }

    private lateinit var viewModel: DeliveryFeeCalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_fee_calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveryFeeCalculatorViewModel::class.java)
        // TODO: Use the ViewModel
    }



}