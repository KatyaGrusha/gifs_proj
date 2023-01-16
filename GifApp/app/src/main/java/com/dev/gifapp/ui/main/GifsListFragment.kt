package com.dev.gifapp.ui.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.gifapp.R
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.dev.gifapp.databinding.FragmentGifsListBinding
import com.dev.gifapp.ui.base.BaseFragment
import com.dev.gifapp.extensions.parcelable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GifsListFragment : BaseFragment<FragmentGifsListBinding>(FragmentGifsListBinding::inflate) {
    private val viewModel: GifsListViewModel by viewModels()
    private var _adapter: GifsListAdapter = GifsListAdapter(::gifClickedById)
    private var savedRecyclerLayoutState: Parcelable? = null

    companion object {
        private const val BUNDLE_KEY = "recycler"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            lifecycleScope.launch {
                viewModel.initList().collect {
                        _adapter.updateListItems(it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            savedRecyclerLayoutState = savedInstanceState.parcelable(BUNDLE_KEY)
            displayData()
        } else {
            initViews()
        }
    }

    private fun initViews() {
        initRVGifs()
        initSearch()
    }

    private fun displayData() {
        displayRVGifs()
        initSearch()
    }

    private fun restoreLayoutManagerPosition() {
        if (savedRecyclerLayoutState != null) {
            binding.rvGifs.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putParcelable(
            BUNDLE_KEY,
            binding.rvGifs.layoutManager?.onSaveInstanceState()
        )
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedRecyclerLayoutState = savedInstanceState?.parcelable(BUNDLE_KEY)
        super.onViewStateRestored(savedInstanceState)
    }

    private fun displayRVGifs() {
        with(binding.rvGifs) {
            layoutManager = LinearLayoutManager(activity)
            _adapter.updateListItems(viewModel.gifsList.value ?: listOf())
            adapter = _adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((binding.rvGifs.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == _adapter.itemCount - 1) {
                        loadData()
                    }
                }
            })
            restoreLayoutManagerPosition()
        }
    }

    private fun initSearch() {
        with(binding.searchView) {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    this@with.setQuery("", false)
                    this@with.clearFocus()
                    updateGifs(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun initRVGifs() {
            with(binding.rvGifs) {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if ((binding.rvGifs.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == _adapter.itemCount - 1) {
                            loadData()
                        }
                    }
                })
            }
    }

    private fun gifClickedById(media: MediaAbstractModel) {
        val bundle = Bundle()
        bundle.putParcelable("gifMedia", media)
        findNavController().navigate(R.id.gifDetailFragment, bundle)
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.loadData().collect {
                _adapter.addNextData(it)
            }
        }
    }

    private fun updateGifs(key: String?) {
        lifecycleScope.launch {
            viewModel.search(key ?: "").collect {
                _adapter.updateListItems(it)
                binding.rvGifs.smoothScrollToPosition(0)
            }
        }
    }
}