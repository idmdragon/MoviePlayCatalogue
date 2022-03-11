package com.idmdragon.people.ui


import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.people.databinding.FragmentPeopleBinding
import com.idmdragon.people.di.peopleModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class PeopleFragment : BaseFragment<PeopleViewModel, FragmentPeopleBinding>() {

    override val viewModel: PeopleViewModel by viewModel()

    override fun getViewBinding(): FragmentPeopleBinding =
        FragmentPeopleBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(peopleModule)
    }
}