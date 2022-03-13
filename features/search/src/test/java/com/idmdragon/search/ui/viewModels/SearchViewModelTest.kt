package com.idmdragon.search.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.usecase.SearchUseCase
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

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

    private var params = "Indonesia"

    @Mock
    private lateinit var useCase: SearchUseCase

    @Mock
    private lateinit var fakeListItems: PagingData<Search>

    private val flowData: Flow<PagingData<Search>> = flow { fakeListItems }

    @Mock
    private lateinit var observer: Observer<PagingData<Search>>

    @Before
    fun setUp() {
        viewModel = SearchViewModel(useCase)
    }

    @Test
    fun `verify getMovieTopRated success`() = runBlockingTest {

        Mockito.`when`(useCase.searchMovieTv(params)).thenReturn(flowData)
        viewModel.searchMovieTv(params).observeForever(observer)

        Mockito.verify(useCase).searchMovieTv(params)
    }
}