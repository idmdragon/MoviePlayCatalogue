package com.idmdragon.search.ui


import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.search.databinding.FragmentSearchBinding
import com.idmdragon.search.di.searchModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val viewModel: SearchViewModel by viewModel()

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(searchModule)
    }

}