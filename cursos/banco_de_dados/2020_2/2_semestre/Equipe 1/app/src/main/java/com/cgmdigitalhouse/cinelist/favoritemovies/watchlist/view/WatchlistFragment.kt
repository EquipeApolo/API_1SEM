package com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.repository.WatchlistRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.viewmodel.WatchlistViewModel
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel.MovieDetailsViewModel
import com.cgmdigitalhouse.cinelist.utils.SwipeToDeleteCallback
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.movies.view.VerticalMovieListAdapter
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.view.MovieOfflineAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class WatchlistFragment : Fragment() {
    lateinit var myView: View
    private lateinit var _viewModel: WatchlistViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var _listMovieCrossRefEntity: MutableList<ListMovieCrossRefEntity>
    private lateinit var _viewAdapter: VerticalMovieListAdapter
    private var _id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_watchlist, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var auth = FirebaseAuth.getInstance()
        var useId = auth.currentUser!!.uid

        _viewModel = ViewModelProvider(
            this,
            WatchlistViewModel.WatchlistViewModelFactory(WatchlistRepository(AppDatabase.getDatabase(myView.context).listMovieCrossRefDao()))
        ).get(WatchlistViewModel::class.java)

        _viewModel.getMovies(useId).observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                _id = it[0].listMovieId
            }
            _listMovieCrossRefEntity = it
            createList()
        })

        _viewModel.getMovies(useId)

        val btnShare = myView.findViewById<FloatingActionButton>(R.id.fbtnShare_watchlistFragment)
        btnShare.setOnClickListener{

            var txtShare = ""
            _viewAdapter.dataset.forEach{ movieModel ->
                txtShare += "\n- ${movieModel.title}"
                movieModel.releaseDate?.let { releaseDate ->
                    txtShare += ", ${releaseDate.split("-")[0]}"
                }
            }
            val intent = Intent()

            intent.action = Intent.ACTION_SEND
            intent.putExtra( Intent.EXTRA_TEXT,
                "Esses são meus filmes favoritos: \n " +
                        "$txtShare \n\n" +
                        "Para saber mais detalhes dos filmes e criar suas próprias listas, " +
                        "baixe o aplicativo Cinelist!")
            intent.type = "text/plain"

            val intentChooser = Intent.createChooser( intent, "Compartilhar lista de filmes com:" )
            startActivity( intentChooser )

        }
    }

    private fun createList() {
        val listMovieModel = mutableListOf<MovieModel>()

        _movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)


        notFound(_listMovieCrossRefEntity.isNotEmpty())

        for (itemListMovie in _listMovieCrossRefEntity){
            _movieDetailsViewModel.getMovieDetails(itemListMovie.movieId.toInt()).observe(viewLifecycleOwner, Observer {
                listMovieModel.add(it)
                addItens(listMovieModel)
            })
        }
    }

    private fun notFound(show: Boolean) {
        myView.findViewById<View>(R.id.notfoundLayout_watchList).visibility = if (show) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun addItens(listMovieModel: MutableList<MovieModel>){

        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_watchlistFragment)

        _viewAdapter = VerticalMovieListAdapter(listMovieModel) {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra(HomeFragment.intentId, it.id)
            startActivity(intent)
        }

        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        DividerItemDecoration.VERTICAL
                )
        )

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = _viewAdapter
        }

        val swipeHandler = object : SwipeToDeleteCallback(myView.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = recyclerView.adapter as VerticalMovieListAdapter
                val position = viewHolder.adapterPosition
                val movieToRemove = adapter.removeAt(position)

                _viewModel.removeMovieFromList(_id, movieToRemove.id).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(myView, "${movieToRemove.title} removido da lista", Snackbar.LENGTH_LONG)
                        .setAction("Desfazer") {

                            adapter.addAt(movieToRemove, position)
                            notFound(_viewAdapter.dataset.isNotEmpty())
                            _viewModel.addMovieToList(_id, movieToRemove.id).observe(viewLifecycleOwner, {
                                Snackbar.make(myView, "${movieToRemove.title} readicionado na lista", Snackbar.LENGTH_SHORT).show()
                            })
                        }
                        .show()
                    notFound(_viewAdapter.dataset.isNotEmpty())
                })
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }


    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}