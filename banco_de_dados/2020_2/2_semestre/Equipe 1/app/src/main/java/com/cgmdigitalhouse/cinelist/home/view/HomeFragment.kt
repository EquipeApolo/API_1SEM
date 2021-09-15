package com.cgmdigitalhouse.cinelist.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.home.repository.MovieRepository
import com.cgmdigitalhouse.cinelist.home.viewmodel.MovieViewModel
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel

const val SPAN_COUNT = 2

class HomeFragment : Fragment() {

    lateinit var _viewModel: MovieViewModel
    lateinit var _view: View

    private var _nowPlayingMovieList = mutableListOf<MovieModel>()
    private var _popularMovieList = mutableListOf<MovieModel>()

    private lateinit var _nowPlayingAdapter: HomeAdapter
    private lateinit var _popularAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManagerPopular =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val viewManagerCinema =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerCardPopular)
        val recyclerViewCinema = view.findViewById<RecyclerView>(R.id.recyclerCardCinema)
        _nowPlayingMovieList = mutableListOf<MovieModel>()
        _popularMovieList = mutableListOf<MovieModel>()

        _nowPlayingAdapter = HomeAdapter(_nowPlayingMovieList) {
            val intent = Intent(view.context, MovieDetailsActivity::class.java)
            intent.putExtra(intentId, it.id)
            startActivity(intent)
        }
        _popularAdapter = HomeAdapter(_popularMovieList) {
            val intent = Intent(view.context, MovieDetailsActivity::class.java)
            intent.putExtra(intentId, it.id)
            startActivity(intent)
        }

        recyclerViewCinema.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerCinema
            adapter = _nowPlayingAdapter
        }

        recyclerViewPopular.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerPopular
            adapter = _popularAdapter

        }

        _viewModel = ViewModelProvider(
            this,
            MovieViewModel.MovieViewModelFactory(MovieRepository())
        ).get(MovieViewModel::class.java)

        _viewModel.getMoviesPopular().observe(viewLifecycleOwner, {
            setMoviesPopular(it)
        })


        _viewModel = ViewModelProvider(
            this,
            MovieViewModel.MovieViewModelFactory(MovieRepository())
        ).get(MovieViewModel::class.java)

        _viewModel.getMoviesNowPaying().observe(viewLifecycleOwner, {
            setMoviesNowPaying(it)
        })

    }

    fun setMoviesPopular(movies: List<MovieModel>) {
        _popularMovieList.addAll(movies)

        _popularAdapter.notifyDataSetChanged()
    }

    fun setMoviesNowPaying(movies: List<MovieModel>) {
        movies.let { _nowPlayingMovieList.addAll(it) }

        _nowPlayingAdapter.notifyDataSetChanged()
    }

    companion object {
        const val intentId = "ID"
    }

}