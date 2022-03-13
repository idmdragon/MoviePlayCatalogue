package com.idmdragon.people.ui.viewModels

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.model.People
import com.idmdragon.domain.usecase.PeopleUseCase
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.constant.ConstantExtras
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
class DetailPeopleViewModelTest {

    private lateinit var viewModel: DetailPeopleViewModel

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
    private lateinit var fakeItems: MovieTv

    @Mock
    private lateinit var mockIntent: Intent

    private var personId: Int = 2

    private val flowData: Flow<Resource<People>> = flow { Resource.Success(fakeItems) }

    @Mock
    private lateinit var observer: Observer<Resource<People>>

    @Before
    fun setUp() {
        viewModel = DetailPeopleViewModel(useCase)
    }

    @Test
    fun `verify default value null`() {
        assertEquals(viewModel.personId, null)
    }

    @Test
    fun  `verify processIntent`() {
        viewModel.processIntent(mockIntent)
        mockIntent.apply {
            viewModel.personId = getIntExtra(ConstantExtras.EXTRAS_PEOPLE_ID,0)
            assertEquals(
                viewModel.personId,
                mockIntent.getIntExtra(ConstantExtras.EXTRAS_PEOPLE_ID, 0)
            )
        }

    }

    @Test
    fun `verify getMovieUpcoming success`() = runBlockingTest {

        `when`(useCase.getPersonById(personId)).thenReturn(flowData)
        viewModel.getPersonById(personId).observeForever(observer)

        verify(useCase).getPersonById(personId)
    }
}