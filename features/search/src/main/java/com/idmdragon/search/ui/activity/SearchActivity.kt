package com.idmdragon.search.ui.activity

import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.base_ui.BaseActivity
import com.idmdragon.base_ui.gone
import com.idmdragon.base_ui.show
import com.idmdragon.movieplay.constant.ConstantExtras
import com.idmdragon.movieplay.constant.ConstantPage
import com.idmdragon.movieplay.constant.ConstantVariable
import com.idmdragon.search.R
import com.idmdragon.search.databinding.ActivitySearchBinding
import com.idmdragon.search.di.searchModule
import com.idmdragon.search.ui.viewModels.SearchViewModel
import com.idmdragon.search.ui.adapter.SearchListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {
    override val viewModel: SearchViewModel by viewModel()
    private val listAdapter by lazy {
        SearchListAdapter().apply {
            onUserClickListener = { id , mediaType ->
                if (mediaType == ConstantVariable.CONS_PERSON){
                    startActivity(
                        Intent(this@SearchActivity, Class.forName(ConstantPage.PAGE_DETAIL_PERSON))
                            .putExtra(ConstantExtras.EXTRAS_PEOPLE_ID,id)
                    )
                }else{
                    startActivity(
                        Intent(this@SearchActivity, Class.forName(ConstantPage.PAGE_DETAIL_MOVIE))
                            .putExtra(ConstantExtras.EXTRAS_MOVIE_ID,id)
                    )
                }
            }
        }
    }

    override fun getViewBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

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

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadSearchItem(query: String) {
        viewModel.searchMovieTv(query).observe(this) { listItem ->
            lifecycleScope.launch {
                binding.rvSearch.apply {
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(this@SearchActivity)
                }

                listAdapter.apply {
                    addLoadStateListener { loadState ->
                        loadState.decideOnState(
                            showLoading = { visible ->
                                showProgressBar(visible)
                                binding.ivSearch.gone()
                            },
                            showEmptyState = {

                            },
                            showError = { message ->
                                binding.ivSearch.gone()
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
                                if (itemCount==0){
                                    Snackbar.make(
                                        binding.root,
                                        getString(R.string.message_empty_search),
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                    binding.ivSearch.show()
                                }
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