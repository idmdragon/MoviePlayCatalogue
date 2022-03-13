package com.idmdragon.people.ui.activites

import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.people.databinding.ActivityDetailPeopleBinding
import com.idmdragon.people.di.peopleModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailPeopleActivity : BaseActivity<DetailPeopleViewModel, ActivityDetailPeopleBinding>() {
    override val viewModel: DetailPeopleViewModel by viewModel()

    override fun getViewBinding(): ActivityDetailPeopleBinding =
        ActivityDetailPeopleBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(peopleModule)
    }
}