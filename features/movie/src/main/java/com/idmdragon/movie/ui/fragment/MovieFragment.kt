package com.idmdragon.movie.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movie.databinding.FragmentMovieBinding
import com.idmdragon.movie.di.movieModule
import com.idmdragon.movie.ui.viewModels.MovieViewModel
import com.idmdragon.movie.utils.DummyData
import com.idmdragon.movieplay.ui.adapter.MovieAdapterBig
import com.idmdragon.movieplay.ui.adapter.MovieAdapterMedium
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFragment : BaseFragment<MovieViewModel,FragmentMovieBinding>() {

    override val viewModel: MovieViewModel by viewModel()

    override fun getViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    override fun loadInjectionModule() {
        loadKoinModules(movieModule)
    }

    override fun setUpView() {
        binding.apply {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView(){
        binding.apply {
             val adapterAdapterBig = MovieAdapterBig(requireContext())
            val adapterMedium = MovieAdapterMedium(requireContext())
            adapterMedium.addItems(DummyData.generateListMovie())
            adapterAdapterBig.addItems(DummyData.generateListMovie())

            rvNowPlaying.adapter = adapterAdapterBig
            rvPopular.adapter = adapterMedium
            rvTopRated.adapter = adapterMedium
            rvUpComing.adapter = adapterMedium

            rvPopular.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvTopRated.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvUpComing.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        }
    }

    override fun setUpObserver() {
        super.setUpObserver()
        viewModel.getMovieNowPlaying().observe(viewLifecycleOwner){ resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listItem ->
                        val adapterNowPlaying = MovieAdapterBig(requireContext())
                        binding.rvNowPlaying.apply {
                            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                            adapterNowPlaying.addItems(listItem)
                            adapter = adapterNowPlaying
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


