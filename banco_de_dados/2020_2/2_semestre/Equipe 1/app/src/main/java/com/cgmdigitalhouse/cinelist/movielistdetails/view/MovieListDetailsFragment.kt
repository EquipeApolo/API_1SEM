package com.cgmdigitalhouse.cinelist.movielistdetails.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view.MovieListFragment
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel.MovieDetailsViewModel
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel.MovieListDetailsViewModel
import com.cgmdigitalhouse.cinelist.utils.SwipeToDeleteCallback
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.movies.view.VerticalMovieListAdapter
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.view.MovieOfflineAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_IMG = "img"
private const val ARG_PARAM_ID= "id"

class MovieListDetailsFragment : Fragment() {

    private var _title: String? = null
    private var _description: String? = null
    private var _img: String? = null
    private var _id: Long? = null
    private  var _movies = mutableListOf<MovieModel>()
    private var imageURI: Uri? = null
    private var _fileReference: String =""

    private lateinit var _viewModel: MovieListDetailsViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var _myView: View


    private lateinit var _mAlertDialog: AlertDialog

    private lateinit var _viewAdapter: VerticalMovieListAdapter

    companion object {
        fun newInstance(title: String, img: String, id: Long) =
            MovieListDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                    putString(ARG_PARAM_IMG, img)
                    putLong(ARG_PARAM_ID, id)
                }
            }

        private const val CARD_CORNER_RADIUS = 20
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _title = it.getString(ARG_PARAM_TITLE)
            _img = it.getString(ARG_PARAM_IMG)
            _id = it.getLong(ARG_PARAM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _myView = inflater.inflate(R.layout.fragment_movie_list_details, container, false)
        return _myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var auth = FirebaseAuth.getInstance()
        var idUse = auth.currentUser!!.uid



        _viewModel = ViewModelProvider(
            this,
            MovieListDetailsViewModel.MovieListDetailsViewModelFactory(
                MovieListDetailsRepository(
                    AppDatabase.getDatabase(_myView.context).listMovieDao(),
                    AppDatabase.getDatabase(_myView.context).listMovieCrossRefDao()
                )
            )
        ).get(MovieListDetailsViewModel::class.java)

        _viewModel.getListDetais(_id!!,idUse).observe(viewLifecycleOwner, Observer { list ->
            setDataMovieDetails(list[0])
        })

        _viewModel.getListMoviesCrossRefEntity(_id!!).observe(viewLifecycleOwner, Observer {
            createList(it)
        })

        bindEvents()
    }

    private fun bindEvents() {
        val btnMenu = _myView.findViewById<ImageView>(R.id.btnMoreVert)
        btnMenu.setOnClickListener {
            showMenu(it, R.menu.list_details_menu)
        }

        val back = _myView.findViewById<ImageView>(R.id.btn_BackListDetails)

        back.setOnClickListener() {
            activity!!.finish()
        }

        val btnShare = _myView.findViewById<FloatingActionButton>(R.id.fbtnShare_movieListDetailsFragmentt)
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
                "Esses são os filmes da minha lista $_title: \n " +
                        "$txtShare \n\n" +
                        "Para saber mais detalhes dos filmes e criar suas próprias listas, " +
                        "baixe o aplicativo Cinelist!")
            intent.type = "text/plain"

            val intentChooser = Intent.createChooser( intent, "Compartilhar lista de filmes com:" )
            startActivity( intentChooser )
        }
    }

    fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(_myView.context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener{
            onOptionsItemSelected(it)
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list_details_edit -> {
                editDialog()
                true
            }
            R.id.list_details_delete -> {
                deleteMovieList()
                true
            }
            else -> false
        }
    }

    private fun notFound(show: Boolean) {
        _myView.findViewById<View>(R.id.notfoundLayout_movieListDetails).visibility = if (show) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun setDataMovieDetails(movieList: ListMovieEntity) {
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("uploads")

        val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
        val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)
        val imgView: ImageView = _myView.findViewById(R.id.img_movieListDetailsFragment)

        _title = movieList.name
        _description = movieList.description


        txtName.text = movieList.name
        txtDesc.text = movieList.description
        if(!movieList.imageURL.isBlank()) {
            storage.child(movieList.imageURL.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(imgView)
            }
        }

//        _img?.let {
//            Glide.with(_myView.context)
//                .load(it)
//                .transform(CenterCrop(), RoundedCorners(CARD_CORNER_RADIUS))
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(imgView)
//        }
    }

    private fun createList(listMovieCrossRefEntity: MutableList<ListMovieCrossRefEntity>) {
        _movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)

        notFound(listMovieCrossRefEntity.isNotEmpty())

        for (listMovie in listMovieCrossRefEntity){
            _movieDetailsViewModel.getMovieDetails(listMovie.movieId.toInt()).observe(viewLifecycleOwner, Observer {
                _movies.add(it)
                addItens(_movies)
            })
        }
    }

    fun addItens(movies :MutableList<MovieModel>){
        val viewManager = LinearLayoutManager(_myView.context)
        val recyclerView =
                _myView.findViewById<RecyclerView>(R.id.recyclerView_movieListDetailsFragment)

        _viewAdapter = VerticalMovieListAdapter(movies) {

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

        val swipeHandler = object : SwipeToDeleteCallback(_myView.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = recyclerView.adapter as VerticalMovieListAdapter
                val position = viewHolder.adapterPosition
                val movieToRemove = adapter.removeAt(position)

                _viewModel.removeMovieFromList(_id!!, movieToRemove.id).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(_myView, "${movieToRemove.title} removido da lista", Snackbar.LENGTH_LONG)
                        .setAction("Desfazer") {

                            adapter.addAt(movieToRemove, position)
                            notFound(_viewAdapter.dataset.isNotEmpty())
                            _viewModel.addMovieToList(_id!!, movieToRemove.id).observe(viewLifecycleOwner, {
                                Snackbar.make(_myView, "${movieToRemove.title} readicionado na lista", Snackbar.LENGTH_SHORT).show()
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

    fun editDialog() {

        val mDialogView =
            LayoutInflater.from(_myView.context).inflate(R.layout.list_dialog, null)
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("uploads")

        val mBuilder = AlertDialog.Builder(_myView.context).setView(mDialogView)
            .setTitle("Editar Lista de Filmes")
        _mAlertDialog = mBuilder.show()

        val btnCancelar = mDialogView.findViewById<Button>(R.id.btn_cancelar)
        val btnCriar = mDialogView.findViewById<Button>(R.id.btn_criar)
        val imgMovie = mDialogView.findViewById<ImageView>(R.id.imv_ImageList)
        btnCriar.text=getString(R.string.editar)

        val edtName = mDialogView.findViewById<TextInputEditText>(R.id.edt_nameListInput)
        val edtDescription = mDialogView.findViewById<TextInputEditText>(R.id.edt_descriptionListInput)

        edtName.setText(this._title!!)
        edtDescription.setText(this._description!!)
        if(!this._img!!.isBlank()) {
            storage.child(this._img!!.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(imgMovie)
            }
        }

        _mAlertDialog.findViewById<ImageView>(R.id.imv_ImageList).setOnClickListener {
            procurarArquivo()
        }

        btnCancelar.setOnClickListener {
            _mAlertDialog.dismiss()
        }

        btnCriar.setOnClickListener {

            val listName = edtName.text.toString().trim()
            val listDescription = edtDescription.text.toString().trim()

            if(listName.isBlank()) {
                Toast.makeText(_myView.context, "Você deve preencher um nome válido para a lista", Toast.LENGTH_LONG).show()
            } else {
                this.editMovieList(_id!!, listName, listDescription)

                val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
                val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)

                txtName.text = listName
                txtDesc.text = listDescription

                _mAlertDialog.dismiss()
            }
        }
    }

    fun procurarArquivo() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, MovieListFragment.CONTENT_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MovieListFragment.CONTENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageURI = data?.data
            _mAlertDialog.findViewById<ImageView>(R.id.imv_ImageList).setImageURI(imageURI)
        }
    }

    private fun editMovieList(id: Long, name: String, description: String) {
        var auth = FirebaseAuth.getInstance()
        var idUse = auth.currentUser!!.uid

        val imgMovie = _myView.findViewById<ImageView>(R.id.img_movieListDetailsFragment)

        if(imageURI != null) {
            imageURI?.run {
                val firebase = FirebaseStorage.getInstance()
                val storage = firebase.getReference("uploads")

                val extension = MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(requireActivity().contentResolver.getType(imageURI!!))

                val fileReference = storage.child("${System.currentTimeMillis()}.${extension}")
                fileReference.putFile(this)
                    .addOnSuccessListener {
                        _fileReference = fileReference.toString()

                        if (name.isBlank()) {
                            Toast.makeText(
                                _myView.context,
                                "Preencha o nome da lista",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            _mAlertDialog.dismiss()

                            _viewModel.editList(id, name, description, _fileReference, idUse, false)
                                .observe(viewLifecycleOwner, Observer {
                                    Toast.makeText(
                                        _myView.context,
                                        "Edição salva com sucesso",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                })
                            storage.child(_fileReference.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
                                Picasso.get().load(it).into(imgMovie)
                            }

                        }


                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            _myView.context,
                            "Falha ao carregar imagem!!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            }
        }else{
            if (name.isBlank()) {
                Toast.makeText(
                    _myView.context,
                    "Preencha o nome da lista",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                _mAlertDialog.dismiss()

                _viewModel.editList(id, name, description, "", idUse, false)
                    .observe(viewLifecycleOwner, Observer {
                        Toast.makeText(
                            _myView.context,
                            "Edição salva com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

            }
        }
    }



    fun deleteMovieList() {

        val mDialogView =
            LayoutInflater.from(_myView.context).inflate(R.layout.dialog_confirm_delete, null)

        val mBuilder = AlertDialog.Builder(_myView.context).setView(mDialogView)
            .setTitle("Deletar lista")
        _mAlertDialog = mBuilder.show()

        val btnCancelar = mDialogView.findViewById<Button>(R.id.btnCancelar_dialogConfirmDelete)
        val btnDeletar = mDialogView.findViewById<Button>(R.id.btnDeletar_dialogConfirmDelete)
        val txtMessage = mDialogView.findViewById<TextView>(R.id.txtConfirm_dialogConfirmDelete)
        txtMessage.text =
            "Tem certeza que deseja deletar a lista $_title? Esta ação não poderá ser desfeita"

        btnCancelar.setOnClickListener {
            _mAlertDialog.dismiss()
        }

        btnDeletar.setOnClickListener {
            _viewModel.deleteList(_id!!).observe(viewLifecycleOwner, Observer {
                Toast.makeText(_myView.context, "Lista excluída com sucesso", Toast.LENGTH_SHORT).show()
            })

            activity?.finish()
        }

    }
}