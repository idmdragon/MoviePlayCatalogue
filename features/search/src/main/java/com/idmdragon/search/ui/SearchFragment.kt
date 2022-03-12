package com.idmdragon.search.ui


import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.search.databinding.FragmentSearchBinding
import com.idmdragon.search.di.searchModule
import com.idmdragon.search.ui.adapter.SearchListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val viewModel: SearchViewModel by viewModel()
    private val listAdapter by lazy {
        SearchListAdapter().apply {
            onUserClickListener = {

            }
        }
    }
    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(searchModule)
    }


    override fun setUpListener() {
        super.setUpListener()
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loadSearchItem(view.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun loadSearchItem(query: String){
        viewModel.searchMovieTv(query).observe(this){ listItem ->
            lifecycleScope.launch {
                binding.rvSearch.apply {
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(requireContext())
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
                                Log.e("Message","message $message")
                            }
                        )
                    }
                    submitData(listItem)

                    loadStateFlow.distinctUntilChanged()
                    loadStateFlow
                        .collectLatest {
                            if(it.refresh is LoadState.NotLoading){
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

        showEmptyState( source.append is LoadState.NotLoading
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

    private fun showProgressBar(state: Boolean){
        binding.progressBar.isVisible = state
    }

}