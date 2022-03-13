package com.idmdragon.people.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.idmdragon.domain.model.People
import com.idmdragon.domain.usecase.PeopleUseCase
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
class PeopleViewModelTest {

    private lateinit var viewModel: PeopleViewModel

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
    private lateinit var useCase: PeopleUseCase

    @Mock
    private lateinit var fakeListItems: PagingData<People>

    private val flowData: Flow<PagingData<People>> = flow { fakeListItems }

    @Mock
    private lateinit var observer: Observer<PagingData<People>>

    @Before
    fun setUp() {
        viewModel = PeopleViewModel(useCase)
    }

    @Test
    fun `verify getMovieTopRated success`() = runBlockingTest {

        `when`(useCase.getPopularPeople()).thenReturn(flowData)
        viewModel.getPopularPeople().observeForever(observer)

        verify(useCase).getPopularPeople()
    }
}