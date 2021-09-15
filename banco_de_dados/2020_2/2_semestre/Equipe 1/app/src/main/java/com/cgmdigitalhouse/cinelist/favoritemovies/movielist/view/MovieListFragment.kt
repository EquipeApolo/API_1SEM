package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.cgmdigitalhouse.cinelist.movielistdetails.view.MovieListDetailsActivity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.lang.System.currentTimeMillis


class MovieListFragment : Fragment() {
    lateinit var myView: View
    lateinit var viewModel: MovieListViewModel
    lateinit var mAlertDialog: AlertDialog
    lateinit var movieLists: MutableList<MovieListModel>
    lateinit var  mDialogView: View
    private lateinit var _view: View
    private var imageURI: Uri? = null
    private var _fileReference: String =""

    private lateinit var progressOverlay: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_movie_list, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MOVIE_LIST_FRAGMENT", "onViewCreated")
        _view = view

        progressOverlay = _view.findViewById(R.id.progress_overlay)
        var auth = FirebaseAuth.getInstance()
        var idUse = auth.currentUser!!.uid

        viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MovieListRepository(AppDatabase.getDatabase(myView.context).listMovieDao()))
        ).get(MovieListViewModel::class.java)

         viewModel.getMovieLists(idUse).observe(viewLifecycleOwner, {
                movieLists = it
                createList()
         })
      
        addItemList()
    }

    private fun createList() {
        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_movieListFragment)


        val viewAdapter = MovieListAdapter(movieLists) {

            val intent = Intent(activity, MovieListDetailsActivity::class.java)
            intent.putExtra(getString(R.string.intent_list_name), it.name)
            intent.putExtra("LIST_ID", it.listMovieId)
            intent.putExtra("LIST_IMAGE", it.imageURL)

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
            adapter = viewAdapter
        }

        notFound(movieLists.isNotEmpty())
    }

    private fun notFound(show: Boolean) {
        myView.findViewById<View>(R.id.notfoundLayout_favoriteMovies).visibility = if (show) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun addItemList(){
        val btnCreaterList = myView.findViewById<FloatingActionButton>(R.id.fbtnCreatList_movieListFragment)
        btnCreaterList.setOnClickListener {
            mDialogView =
                    LayoutInflater.from(myView.context).inflate(R.layout.list_dialog, null)

            val mBuilder = AlertDialog.Builder(myView.context).setView(mDialogView)
                    .setTitle("Criar Lista de Filmes")
            mAlertDialog = mBuilder.show()

            val btnCancelar = mDialogView.findViewById<Button>(R.id.btn_cancelar)
            val btnCriar = mDialogView.findViewById<Button>(R.id.btn_criar)

            mAlertDialog.findViewById<ImageView>(R.id.imv_ImageList).setOnClickListener {
                procurarArquivo()
            }

            btnCancelar.setOnClickListener {
                mAlertDialog.dismiss()
            }

            btnCriar.setOnClickListener {
                saveList()

            }
        }
    }
    fun  saveList(){
        val edtName = mDialogView.findViewById<TextInputEditText>(R.id.edt_nameListInput)
        val edtDescription = mDialogView.findViewById<TextInputEditText>(R.id.edt_descriptionListInput)

        val listName = edtName.text.toString().trim()
        val listDescription = edtDescription.text.toString().trim()

        var auth = FirebaseAuth.getInstance()
        var idUse = auth.currentUser!!.uid

        if(imageURI != null) {
            imageURI?.run {
                val firebase = FirebaseStorage.getInstance()
                val storage = firebase.getReference("uploads")

                val extension = MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(requireActivity().contentResolver.getType(imageURI!!))

                val fileReference = storage.child("${currentTimeMillis()}.${extension}")
                showOverlay()
                fileReference.putFile(this)
                    .addOnSuccessListener {
                        _fileReference = fileReference.toString()

                        if (listName.isBlank()) {
                            Toast.makeText(
                                myView.context,
                                "Preencha o nome da lista",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            mAlertDialog.dismiss()

                            viewModel.inserirListMovie(
                                listName,
                                listDescription,
                                _fileReference,
                                idUse,
                                false
                            ).observe(viewLifecycleOwner, {
                                movieLists.add(
                                    MovieListModel(
                                        it.listMovieId,
                                        it.name,
                                        it.description,
                                        0,
                                        it.imageURL
                                    )
                                )
                                createList()
                            })
                        }


                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            _view.context,
                            "Falha ao carregar imagem!!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }.addOnCompleteListener {
                        hideOverlay()
                    }
            }
        }else{

            if (listName.isBlank()) {
                Toast.makeText(
                    myView.context,
                    "Preencha o nome da lista",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mAlertDialog.dismiss()

                viewModel.inserirListMovie(
                    listName,
                    listDescription,
                    _fileReference,
                    idUse,
                    false
                ).observe(viewLifecycleOwner, {
                    movieLists.add(
                        MovieListModel(
                            it.listMovieId,
                            it.name,
                            it.description,
                            0,
                            it.imageURL
                        )
                    )
                    createList()
                })
            }

        }


    }


    private fun hideOverlay() {
        progressOverlay.visibility = View.INVISIBLE
    }

    private fun showOverlay() {
        progressOverlay.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Log.d("MOVIE_LIST_FRAGMENT", "onResume")
        var auth = FirebaseAuth.getInstance()
        var idUse = auth.currentUser!!.uid

        viewModel.getMovieLists(idUse).observe(viewLifecycleOwner, {
            movieLists = it
            createList()
        })
    }
    fun procurarArquivo() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTENT_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            imageURI = data?.data
            mAlertDialog.findViewById<ImageView>(R.id.imv_ImageList).setImageURI(imageURI)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = MovieListFragment()

        const val CONTENT_REQUEST_CODE = 1

    }
}