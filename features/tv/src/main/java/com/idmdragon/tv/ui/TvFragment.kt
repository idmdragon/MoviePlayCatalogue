package com.idmdragon.tv.ui


import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.tv.databinding.FragmentTvBinding
import com.idmdragon.tv.di.tvModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class TvFragment : BaseFragment<TvViewModel, FragmentTvBinding>() {

    override val viewModel: TvViewModel by viewModel()

    override fun getViewBinding(): FragmentTvBinding =
        FragmentTvBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(tvModule)
    }
}