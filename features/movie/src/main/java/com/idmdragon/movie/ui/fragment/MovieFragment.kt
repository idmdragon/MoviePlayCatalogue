package com.idmdragon.movie.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.idmdragon.base_ui.BaseFragment
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
             val adapterMedium = MovieAdapterMedium(requireContext())
             val adapterAdapterBig = MovieAdapterBig(requireContext())
            adapterMedium.addItems(DummyData.generateListMovie())
            adapterAdapterBig.addItems(DummyData.generateListMovie())

            rvNowPlaying.adapter = adapterAdapterBig
            rvPopular.adapter = adapterMedium
            rvTopRated.adapter = adapterMedium
            rvUpComing.adapter = adapterMedium

            rvNowPlaying.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvPopular.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvTopRated.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvUpComing.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        }
    }


}