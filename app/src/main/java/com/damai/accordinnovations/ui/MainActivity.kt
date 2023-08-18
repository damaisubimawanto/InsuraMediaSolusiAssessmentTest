package com.damai.accordinnovations.ui

import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.ActivityMainBinding
import com.damai.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun ActivityMainBinding.viewInitialization() {
        TODO("Not yet implemented")
    }
}