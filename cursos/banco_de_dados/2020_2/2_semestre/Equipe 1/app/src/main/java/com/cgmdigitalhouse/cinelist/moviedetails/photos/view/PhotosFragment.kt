package com.cgmdigitalhouse.cinelist.moviedetails.photos.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.photos.model.PosterModel
import com.cgmdigitalhouse.cinelist.moviedetails.photos.repository.PhotosRepository
import com.cgmdigitalhouse.cinelist.moviedetails.photos.viewModel.PhotosViewModel

private const val ARG_PARAM1 = "ID"

class PhotosFragment : Fragment() {

    lateinit var _view: View
    private lateinit var _viewModel: PhotosViewModel
    private var param1: Int = 0
    private lateinit var _imgPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_photos, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _viewModel = ViewModelProvider(
                this,
                PhotosViewModel.PhotosViewModelFactory(PhotosRepository())
        ).get(PhotosViewModel::class.java)

        _viewModel.getPhotos(param1).observe(viewLifecycleOwner, {
            createPhotoList(it.posters)
        })
    }

    fun createPhotoList(photos: List<PosterModel>) {
        val viewManagerPhotos = GridLayoutManager(activity, 2)
        val recyclerViewPhotos = view?.findViewById<RecyclerView>(R.id.rcyVwPhotos)

        val viewAdapterPhotos = PhotosAdapter(photos) {
            val poster = it

            val intent = Intent(view!!.context, PhotoFullActivity::class.java)
            intent.putExtra("IMAGE", poster.getPathPoster())
            startActivity(intent)

        }

        recyclerViewPhotos?.apply {
            layoutManager = viewManagerPhotos
            adapter = viewAdapterPhotos
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            PhotosFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

}