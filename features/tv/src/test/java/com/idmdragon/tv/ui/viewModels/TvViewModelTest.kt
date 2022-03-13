package com.idmdragon.tv.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.usecase.TvUseCase
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
import org.junit.Assert.*
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

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
    private lateinit var useCase: TvUseCase

    @Mock
    private lateinit var fakeListItems: List<MovieTv>

    private val flowData: Flow<Resource<List<MovieTv>>> = flow { Resource.Success(fakeListItems) }

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieTv>>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(useCase)
    }

    @Test
    fun `verify getTvAiringToday success`() = runBlockingTest {

        `when`(useCase.getTvAiringToday()).thenReturn(flowData)
        viewModel.getTvAiringToday().observeForever(observer)

        verify(useCase).getTvAiringToday()
    }

    @Test
    fun `verify getTvOnTheAir success`() = runBlockingTest {

        `when`(useCase.getTvOnTheAir()).thenReturn(flowData)
        viewModel.getTvOnTheAir().observeForever(observer)

        verify(useCase).getTvOnTheAir()
    }

    @Test
    fun `verify getTvPopular success`() = runBlockingTest {

        `when`(useCase.getTvPopular()).thenReturn(flowData)
        viewModel.getTvPopular().observeForever(observer)

        verify(useCase).getTvPopular()
    }

    @Test
    fun `verify getMovieTopRated success`() = runBlockingTest {

        `when`(useCase.getTvTopRated()).thenReturn(flowData)
        viewModel.getTvTopRated().observeForever(observer)

        verify(useCase).getTvTopRated()
    }
}