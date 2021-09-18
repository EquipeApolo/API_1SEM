package com.cgmdigitalhouse.cinelist.search.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.search.repository.SearchRepository
import com.cgmdigitalhouse.cinelist.search.viewmodel.SearchViewModel
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.movies.view.VerticalMovieListAdapter

class SearchFragment : Fragment() {

    private lateinit var _viewModel: SearchViewModel
    private lateinit var _myView: View
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _movieListAdapter: VerticalMovieListAdapter

    private var _text: String? = null
    private var _movieList = mutableListOf<MovieModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _myView = inflater.inflate(R.layout.fragment_search, container, false)
        return _myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        _viewModel = ViewModelProvider(
            this,
            SearchViewModel.SearchViewModelFactory(
                SearchRepository()
            )
        ).get(SearchViewModel::class.java)

        _viewModel.getMovies().observe(viewLifecycleOwner, {
            showResults(it)
        })

        showLoading(true)
        initSearch()
        setInfiniteScroll()
    }

    private fun initRecyclerView() {
        _recyclerView = _myView.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        val manager = LinearLayoutManager(_myView.context)

        _movieListAdapter = VerticalMovieListAdapter(_movieList) {
            val intent = Intent(_myView.context, MovieDetailsActivity::class.java)
            intent.putExtra(HomeFragment.intentId, it.id)
            startActivity(intent)
        }

        _recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _movieListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun showResults(movies: List<MovieModel>?, clearList: Boolean=true) {
        showLoading(false)


        if(clearList) {
            _movieList.clear()
            movies?.isNotEmpty()?.let { notfound(it) }
        }

        movies?.let {
            _movieList.addAll(it)
        }

        _movieListAdapter.notifyDataSetChanged()
    }

    fun notfound(notfound: Boolean) {
        _myView.findViewById<View>(R.id.notfoundLayout_searchFragment).visibility = if (notfound) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun initSearch() {
        val searchView = _myView.findViewById<SearchView>(R.id.searchView_searchFragment)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                _text = query
                _viewModel.search(query).observe(viewLifecycleOwner, {
                    showResults(it)
                })

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    showResults(_viewModel.oldMovieList())
                }

                return true
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _myView.findViewById<View>(R.id.loading_searchFragment)

        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    private fun setInfiniteScroll() {
        _recyclerView.run {
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as LinearLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = (lastVisible + 5) >= totalItemCount

                    if(totalItemCount > 0 && lastItem) {
                        _viewModel.proximaPagina(_text!!).observe(viewLifecycleOwner, {
                            showResults(it, false)
                        })
                    }
                }
            })
        }
    }

}