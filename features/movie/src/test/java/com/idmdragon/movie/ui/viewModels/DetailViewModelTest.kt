package com.idmdragon.movie.ui.viewModels

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.constant.ConstantExtras
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var fakeItems: MovieTv

    @Mock
    private lateinit var mockIntent: Intent

    private var movieId: Int = 2
    private var movieType: String = "TYPE_POPULAR"

    private val flowData: Flow<Resource<MovieTv>> = flow { Resource.Success(fakeItems) }

    @Mock
    private lateinit var observer: Observer<Resource<MovieTv>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `verify default value null`() {
        assertEquals(viewModel.movieId,null)
        assertEquals(viewModel.movieType,null)
    }

    @Test
    fun  `verify processIntent`() {
        mockIntent.apply {
            viewModel.movieId = getIntExtra(ConstantExtras.EXTRAS_MOVIE_ID,0)
            viewModel.movieType = getStringExtra(ConstantExtras.EXTRAS_MOVIE_TYPE)
        }
        assertEquals(viewModel.movieId,mockIntent.getIntExtra(ConstantExtras.EXTRAS_MOVIE_ID,0))
        assertEquals(viewModel.movieType,mockIntent.getStringExtra(ConstantExtras.EXTRAS_MOVIE_TYPE))
    }

    @Test
    fun `verify getMovieUpcoming success`() = runBlockingTest {

        `when`(useCase.getMovieDetail(movieId,movieType)).thenReturn(flowData)
        viewModel.getDetailMovie(movieId,movieType).observeForever(observer)

        verify(useCase).getMovieDetail(movieId,movieType)
    }
}