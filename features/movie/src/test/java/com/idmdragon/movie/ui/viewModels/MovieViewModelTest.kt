package com.idmdragon.movie.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.utils.Resource
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

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
    private lateinit var fakeListItems: List<MovieTv>

    private val flowData: Flow<Resource<List<MovieTv>>> = flow { Resource.Success(fakeListItems) }

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieTv>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun `verify getMovieUpcoming success`() = runBlockingTest {

        `when`(useCase.getMovieUpcoming()).thenReturn(flowData)
        viewModel.getMovieUpComing().observeForever(observer)

        verify(useCase).getMovieUpcoming()
    }

    @Test
    fun `verify getMovieNowPlaying success`() = runBlockingTest {

        `when`(useCase.getMovieNowPlaying()).thenReturn(flowData)
        viewModel.getMovieNowPlaying().observeForever(observer)

        verify(useCase).getMovieNowPlaying()
    }

    @Test
    fun `verify getMoviePopular success`() = runBlockingTest {

        `when`(useCase.getMoviePopular()).thenReturn(flowData)
        viewModel.getMoviePopular().observeForever(observer)

        verify(useCase).getMoviePopular()
    }

    @Test
    fun `verify getMovieTopRated success`() = runBlockingTest {

        `when`(useCase.getMovieTopRated()).thenReturn(flowData)
        viewModel.getMovieTopRated().observeForever(observer)

        verify(useCase).getMovieTopRated()
    }
}