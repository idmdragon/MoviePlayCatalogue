package com.idmdragon.movie.ui.activities

import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movie.databinding.ActivityDetailBinding
import com.idmdragon.movie.di.movieModule
import com.idmdragon.movie.ui.viewModels.DetailViewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailActivity : BaseActivity<DetailViewModels,ActivityDetailBinding>() {

    override val viewModel: DetailViewModels by viewModel()

    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(movieModule)
    }


    override fun setUpObserver() {

    }

}