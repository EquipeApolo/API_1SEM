package com.cgmdigitalhouse.cinelist.moviedetails.details.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.cgmdigitalhouse.cinelist.moviedetails.cast.model.CastModel
import com.cgmdigitalhouse.cinelist.moviedetails.cast.repository.CastRepository
import com.cgmdigitalhouse.cinelist.moviedetails.cast.view.CastAdapter
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.photos.view.PhotosFragment
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.view.ReviewsFragment
import com.cgmdigitalhouse.cinelist.moviedetails.cast.viewModel.CastViewModel
import com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel.MovieDetailsViewModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.google.firebase.auth.FirebaseAuth

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var _castViewModel: CastViewModel
    private lateinit var _viewModel: MovieDetailsViewModel
    private lateinit var _mAlertDialog: AlertDialog
    private lateinit var _listMovies: Spinner
    private var _position: Int = 0
    private lateinit var _movieListViewModel: MovieListViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private var movieLists: MutableList<ListMovieEntity> = mutableListOf()

    private var _movieDetails: MovieModel? = null

    private lateinit var _imgMovie: ImageView
    private lateinit var _imgAddMovie: ImageView
    private lateinit var _movieTitle: TextView
    private lateinit var _movieRate: TextView
    private lateinit var _movieYear: TextView
    private lateinit var _movieSummary: TextView
    private lateinit var _movieGenre: TextView
    private lateinit var _movieTime: TextView
    private lateinit var listMovies: ArrayList<String>
    private lateinit var mDialogView: View
    private var _id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _imgMovie = findViewById(R.id.img_movieDetails)
        _movieTitle = findViewById(R.id.txt_nameMovieDetails)
        _movieRate = findViewById(R.id.txt_rateMovieDetails)
        _movieYear = findViewById(R.id.txt_yearMovieDetails)
        _movieGenre = findViewById(R.id.txt_genreMovieDetails)
        _movieTime = findViewById(R.id.txt_timeMovieDetails)
        _movieSummary = findViewById(R.id.txtSinopseValue_movieDetails)
        _imgAddMovie = findViewById(R.id.imvAddMovieList)

        _id = intent.getIntExtra(HomeFragment.intentId, 550)

        createMovieDetails()

        val back = findViewById<ImageView>(R.id.btn_BackMovieDetails)

        back.setOnClickListener() {
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createMovieDetails() {
        val auth = FirebaseAuth.getInstance()
        val idUse = auth.currentUser!!.uid

        _viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)

        _viewModel.getMovieDetails(_id).observe(this, {
            _movieDetails = it

            val runtime = _movieDetails!!.runtime
            val hours: Int = runtime / 60
            val minutes: Int = runtime % 60

            _movieTitle.text = _movieDetails!!.title
            _movieRate.text = _movieDetails!!.voteAverage.toString()
            _movieGenre.text = _movieDetails!!.genres[0].name
            _movieTime.text = "%dh %02dmin".format(hours, minutes)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                _movieSummary.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }

            _movieSummary.text = _movieDetails!!.overview
            _movieDetails!!.releaseDate?.let {
                _movieYear.text = it.split("-")[0].toString()
            }

            Glide.with(this)
                .load(_movieDetails!!.getPathPoster())
                .transform()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(_imgMovie)

            createFragments()
            createCastView()
        })

        _imgAddMovie.setOnClickListener {

             mDialogView =
                    LayoutInflater.from(this).inflate(R.layout.add_movie_dialog, null)

            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
                    .setTitle("Adicionar filme à lista")
            _mAlertDialog = mBuilder.show()


            _listMovies = mDialogView.findViewById(R.id.list_movie_spinner)


            _movieListViewModel = ViewModelProvider(
                    this,
                    MovieListViewModel.MovieListViewModelFactory(MovieListRepository(AppDatabase.getDatabase(this).listMovieDao()))
            ).get(MovieListViewModel::class.java)

            _movieListViewModel.getAllMovieLists(idUse).observe(this, Observer{
                createListDialogDetail(it)
            })
        }
    }

    fun createListDialogDetail(listaMovies : MutableList<ListMovieEntity>){
        movieLists = listaMovies
        listMovies = arrayListOf<String>()
        for(item in movieLists){
            listMovies.add(item.name)
        }

        val btnCancelar = mDialogView.findViewById<Button>(R.id.btnCancelarMovie)
        val btnAdicionar = mDialogView.findViewById<Button>(R.id.btnAdicionarMovie)
        _listMovies.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listMovies)

        _listMovies.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                _position = p2
            }

        }
        btnCancelar.setOnClickListener {
            _mAlertDialog.dismiss()
        }



        btnAdicionar.setOnClickListener {

            _movieDetailsViewModel = ViewModelProvider(
                    this,
                    MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
            ).get(MovieDetailsViewModel::class.java)
            _movieDetailsViewModel.repository.listMovieCrossRefDao = AppDatabase.getDatabase(this).listMovieCrossRefDao()

            _movieDetailsViewModel.checkIfMovieIsOnList( _id.toLong(),  movieLists[_position].listMovieId).observe(this, Observer{
                if(it[0].toInt() == 0) {
                    _movieDetailsViewModel.addMovieToList( _id.toLong(),  movieLists[_position].listMovieId).observe(this, Observer{
                        Toast.makeText(this, "Filme inserido na lista com sucesso!", Toast.LENGTH_SHORT).show()

                        _mAlertDialog.dismiss()
                    })
                } else {
                    Toast.makeText(this, "Esse filme já foi adicionado nesta lista!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun createCastView() {
        _castViewModel = ViewModelProvider(
            this,
            CastViewModel.CastViewModelFactory(CastRepository())
        ).get(CastViewModel::class.java)

        _castViewModel.getCast(_id).observe(this, {
            createCastList(it!!)
        })
    }

    private fun createCastList(cast: List<CastModel>) {
        val viewManagerElenco = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerViewElenco = findViewById<RecyclerView>(R.id.rcyVwCast)
        val viewAdapterElenco = CastAdapter(cast)

        recyclerViewElenco.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerElenco
            adapter = viewAdapterElenco
        }
    }

    private fun createFragments() {
        val pager = findViewById<ViewPager>(R.id.viewPagerMovieDetails)

        val fragments =  listOf(
            PhotosFragment.newInstance(_id),
            ReviewsFragment.newInstance(_id)
        )

        val titulos = listOf(
            getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = FragmentsAdapter(fragments, titulos, supportFragmentManager)
    }
}