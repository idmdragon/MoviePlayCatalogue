package com.idmdragon.people.ui.fragment


import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.movieplay.constant.ConstantExtras.EXTRAS_PEOPLE_ID
import com.idmdragon.people.databinding.FragmentPeopleBinding
import com.idmdragon.people.di.peopleModule
import com.idmdragon.people.ui.activites.DetailPeopleActivity
import com.idmdragon.people.ui.viewModels.PeopleViewModel
import com.idmdragon.people.ui.adapter.PeopleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class PeopleFragment : BaseFragment<PeopleViewModel, FragmentPeopleBinding>() {

    override val viewModel: PeopleViewModel by viewModel()

    private val listAdapter by lazy {
        PeopleAdapter().apply {
            onClickListener = {
                startActivity(Intent(requireContext(),DetailPeopleActivity::class.java)
                    .putExtra(EXTRAS_PEOPLE_ID,it)
                )
            }
        }
    }
    override fun getViewBinding(): FragmentPeopleBinding =
        FragmentPeopleBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(peopleModule)
    }

    override fun setUpObserver() {
        super.setUpObserver()
        viewModel.getPopularPeople().observe(viewLifecycleOwner) { listItem ->
            lifecycleScope.launch {
                binding.rvPeople.apply {
                    adapter = listAdapter
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }

                listAdapter.apply {
                    addLoadStateListener { loadState ->
                        loadState.decideOnState(
                            showLoading = { visible ->
                                showProgressBar(visible)
                            },
                            showEmptyState = { visible ->
                            },
                            showError = { message ->
                                Snackbar.make(
                                    binding.root,
                                    message,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                    submitData(listItem)

                    loadStateFlow.distinctUntilChanged()
                    loadStateFlow
                        .collectLatest {
                            if (it.refresh is LoadState.NotLoading) {
                                showProgressBar(false)
                            }
                        }
                }

            }
        }
    }

    private inline fun CombinedLoadStates.decideOnState(
        showLoading: (Boolean) -> Unit,
        showEmptyState: (Boolean) -> Unit,
        showError: (String) -> Unit
    ) {
        showLoading(refresh is LoadState.Loading)

        showEmptyState(
            source.append is LoadState.NotLoading
                    && source.append.endOfPaginationReached
                    && listAdapter.itemCount == 0
        )

        val errorState = source.append as? LoadState.Error
            ?: source.prepend as? LoadState.Error
            ?: source.refresh as? LoadState.Error
            ?: append as? LoadState.Error
            ?: prepend as? LoadState.Error
            ?: refresh as? LoadState.Error

        errorState?.let { showError(it.error.toString()) }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
    }

}