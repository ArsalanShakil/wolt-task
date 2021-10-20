/*
 * Copyright (c) 2021 by Arsalan Shakil.
 * Wolt task project.
 *
 */

package com.example.wolttask.dependencyinjection

import com.example.wolttask.helpers.DeliveryCostHelper
import com.example.wolttask.ui.deliveryfee.DeliveryFeeCalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModules : Module = module {
    single { DeliveryCostHelper() }
}
private val viewModelModule: Module = module {
    viewModel { DeliveryFeeCalculatorViewModel(get()) }
}

val modules =
    listOf(appModules, viewModelModule)
