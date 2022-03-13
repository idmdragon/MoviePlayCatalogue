package com.idmdragon.movieplay.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.movieplay.databinding.ActivitySplashBinding
import com.idmdragon.movieplay.di.splashModule
import com.idmdragon.movieplay.ui.main.MainActivity
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashViewModel,ActivitySplashBinding>(){
    override val viewModel: SplashViewModel by viewModel()

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(splashModule)
    }

    override fun setUpObserver() {
        super.setUpObserver()

        CoroutineScope(Dispatchers.Main).launch {
            if (isNetworkConnected()){
                    viewModel.clearData()
            }
            delay(1500)

            startActivity(Intent(this@SplashActivity,MainActivity::class.java)).also {
                finish()
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

}