package com.idmdragon.people.ui.activites

import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.domain.model.People
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.BuildConfig
import com.idmdragon.movieplay.R
import com.idmdragon.people.databinding.ActivityDetailPeopleBinding
import com.idmdragon.people.di.peopleModule
import com.idmdragon.people.ui.viewModels.DetailPeopleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailPeopleActivity : BaseActivity<DetailPeopleViewModel, ActivityDetailPeopleBinding>() {
    override val viewModel: DetailPeopleViewModel by viewModel()

    override fun getViewBinding(): ActivityDetailPeopleBinding =
        ActivityDetailPeopleBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(peopleModule)
    }

    override fun setUpObserver() {
        viewModel.processIntent(intent)

        viewModel.getPersonById(viewModel.personId ?: 0)
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

    private fun populateData(person: People) {
        binding.apply {
            with(person) {
                tvFullName.text = name
                tvBiography.text = biography
                tvPopularity.text = popularity.toString()
                tvBirthday.text = birthday
                tvDepartment.text = knownForDepartment
                tvGender.text = if (gender == 1) {
                    "Female"
                } else {
                    "Male"
                }

                Glide.with(this@DetailPeopleActivity)
                    .load(BuildConfig.BASE_IMAGE_URL + profilePath)
                    .placeholder(
                        ColorDrawable(
                            ContextCompat.getColor(
                                this@DetailPeopleActivity,
                                R.color.gray
                            )
                        )
                    ).transform(CenterCrop(), RoundedCorners(10)).into(ivPhoto)
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