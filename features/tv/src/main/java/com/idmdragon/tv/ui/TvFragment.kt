package com.idmdragon.tv.ui


import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.ui.adapter.MovieAdapterBig
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

    override fun setUpObserver() {
        super.setUpObserver()
        viewModel.getTvAiringToday().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterTvAiringToday = MovieAdapterBig(requireContext())
                        binding.rvAiringToday.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapterTvAiringToday.addItems(listItem)
                            adapter = adapterTvAiringToday
                        }
                    }
                }
                is Resource.Loading -> {

                }

                is Resource.Error -> {
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
                        val adapterOnTheAIr = MovieAdapterBig(requireContext())
                        binding.rvOnTheAir.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapterOnTheAIr.addItems(listItem)
                            adapter = adapterOnTheAIr
                        }
                    }
                }
                is Resource.Loading -> {

                }

                is Resource.Error -> {
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