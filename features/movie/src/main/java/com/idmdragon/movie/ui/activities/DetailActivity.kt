package com.idmdragon.movie.ui.activities

import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movie.databinding.ActivityDetailBinding
import com.idmdragon.movie.di.movieModule
import com.idmdragon.movie.ui.viewModels.DetailViewModels
import com.idmdragon.movieplay.BuildConfig
import com.idmdragon.movieplay.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailActivity : BaseActivity<DetailViewModels, ActivityDetailBinding>() {

    override val viewModel: DetailViewModels by viewModel()

    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(movieModule)
    }


    override fun setUpObserver() {
        viewModel.processIntent(intent)

        viewModel.getDetailMovie(viewModel.movieId ?: 0, viewModel.movieType.orEmpty())
            .observe(this) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        resource.data?.let {
                            populateData(it)
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Snackbar.make(
                            binding.root,
                            resource.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun populateData(movie: Movie) {
        binding.apply {
            with(movie) {
                tvMovieTitle.text = originalTitle
                tvOverview.text = overview
                tvPopularity.text = popularity.toString()
                tvReleaseDate.text = releaseDate
                tvVoteAvarage.text = voteAverage.toString()
                tvVoteCount.text = voteCount.toString()

                Glide.with(this@DetailActivity)
                    .load(BuildConfig.BASE_IMAGE_URL + posterPath)
                    .placeholder(
                        ColorDrawable(
                            ContextCompat.getColor(
                                this@DetailActivity,
                                R.color.gray
                            )
                        )
                    ).transform(CenterCrop(), RoundedCorners(10)).into(ivPoster)
            }
        }
    }

    override fun setUpListener() {
        super.setUpListener()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }


}