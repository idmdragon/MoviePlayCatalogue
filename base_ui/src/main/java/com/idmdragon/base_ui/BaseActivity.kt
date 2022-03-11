package com.idmdragon.base_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM: ViewModel, VB:ViewBinding>: AppCompatActivity() {

    protected lateinit var binding:VB

    protected abstract val viewModel:VM?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadInjectionModule()
        binding = getViewBinding()
        setContentView(binding.root)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUpListener()
        setUpView()
        setUpObserver()
    }

    abstract fun getViewBinding():VB

    protected open fun setUpView(){}

    protected open fun setUpListener(){}

    abstract fun loadInjectionModule()

    protected open fun setUpObserver(){}

}