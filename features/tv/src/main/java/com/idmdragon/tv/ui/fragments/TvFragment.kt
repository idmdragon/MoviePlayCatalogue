package com.idmdragon.tv.ui.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.base_ui.hide
import com.idmdragon.base_ui.show
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.ui.adapter.MovieAdapterLarge
import com.idmdragon.tv.databinding.FragmentTvBinding
import com.idmdragon.tv.di.tvModule
import com.idmdragon.tv.ui.viewModels.TvViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class TvFragment : BaseFragment<TvViewModel, FragmentTvBinding>() {

    override val viewModel: TvViewModel by viewModel()

    override fun getViewBinding(): FragmentTvBinding =
        FragmentTvBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(tvModule)
    }

    override fun setUpObserver() {
        super.setUpObserver()
        viewModel.getTvAiringToday().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterTvAiringToday = MovieAdapterLarge(requireContext())
                        binding.rvAiringToday.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            show()
                            adapterTvAiringToday.addItems(listItem)
                            adapter = adapterTvAiringToday
                        }
                        binding.shimmerAiring.apply {
                            hide()
                            stopShimmer()
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.shimmerAiring.show()
                }

                is Resource.Error -> {
                    binding.shimmerAiring.apply {
                        hide()
                        stopShimmer()
                    }

                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.getTvOnTheAir().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterOnTheAIr = MovieAdapterLarge(requireContext())
                        binding.rvOnTheAir.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            show()
                            adapterOnTheAIr.addItems(listItem)
                            adapter = adapterOnTheAIr
                        }
                        binding.shimmerOnTheAir.apply {
                            hide()
                            stopShimmer()
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.shimmerOnTheAir.show()
                }

                is Resource.Error -> {
                    binding.shimmerOnTheAir.apply {
                        hide()
                        stopShimmer()
                    }
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
        viewModel.getTvPopular().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterPopular = MovieAdapterLarge(requireContext())
                        binding.rvPopular.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapterPopular.addItems(listItem)
                            show()
                            adapter = adapterPopular
                        }
                        binding.shimmerPopular.apply {
                            hide()
                            stopShimmer()
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.shimmerPopular.show()
                }

                is Resource.Error -> {
                    binding.shimmerPopular.apply {
                        hide()
                        stopShimmer()
                    }
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.getTvTopRated().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterTopRated = MovieAdapterLarge(requireContext())
                        binding.rvTopRated.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapterTopRated.addItems(listItem)
                            adapter = adapterTopRated
                            show()
                        }
                        binding.shimmerTopRated.apply {
                            hide()
                            stopShimmer()
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.shimmerTopRated.show()
                }

                is Resource.Error -> {
                    binding.shimmerTopRated.apply {
                        hide()
                        stopShimmer()
                    }
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }


    }
}