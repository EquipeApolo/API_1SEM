package com.cgmdigitalhouse.cinelist.account.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cgmdigitalhouse.cinelist.LoginActivity
import com.cgmdigitalhouse.cinelist.MainActivity
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.account.repository.AccountRepository
import com.cgmdigitalhouse.cinelist.account.viewmodel.AccountViewModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view.MovieListFragment.Companion.CONTENT_REQUEST_CODE
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView

class AccountFragment : Fragment() {
    lateinit var _view: View
    private lateinit var _viewModel: AccountViewModel

    private lateinit var _auth: FirebaseAuth
    private lateinit var _edtName: TextView
    private lateinit var _edtEmail: TextView
    private lateinit var _edtPassword: TextView
    private lateinit var _btnSair: Button
    private lateinit var _imgAddPhoto: CircleImageView
    private lateinit var _email: String
    private lateinit var _user: FirebaseUser
    private var _imageURI: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_account, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _edtName = view.findViewById(R.id.txtEditName_accountFragment)
        _edtEmail = view.findViewById(R.id.txtEditEmail_accountFragment)
        _edtPassword = view.findViewById(R.id.txtEditPassword_accountFragment)
        _btnSair = _view.findViewById(R.id.btn_sair)
        _imgAddPhoto = _view.findViewById(R.id.img_usuario)

        _auth = Firebase.auth
        _user = _auth.currentUser!!
        _email = _user.email.toString()

        _viewModel = ViewModelProvider(
            this,
            AccountViewModel.AccountViewModelFactory(AccountRepository(_view.context))
        ).get(AccountViewModel::class.java)

        _viewModel.account.observe(viewLifecycleOwner, Observer {
            val _name = _view.findViewById<TextView>(R.id.txt_nomeUsuario)
            val _email = _view.findViewById<TextView>(R.id.txt_emailUsuario)
            val noImage = R.drawable.ic_round_person_24

            _name.text = it.name
            _email.text = it.email

            if (it.image == null) {
                Glide.with(view.context)
                    .load(noImage)
                    .circleCrop()
                    .into(_imgAddPhoto)
            } else {
                Glide.with(_view.context)
                    .load(it.image)
                    .circleCrop()
                    .into(_imgAddPhoto)
            }
        })

        _viewModel.getAccount()

        _btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            LoginManager.getInstance().logOut()
            val intent = Intent(_view.context, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }

        _edtName.setOnClickListener {
            val intent = Intent(_view.context, ChangeNameActivity::class.java)
            startActivity(intent)
        }

        _edtEmail.setOnClickListener {
            if (_user.providerData[1].providerId == "google.com" || _user.providerData[1].providerId == "facebook.com") {
                Toast.makeText(
                    _view.context, "Não é possível trocar de e-mail com login social.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(_view.context, ChangeEmailActivity::class.java)
                startActivity(intent)
            }
        }

        _edtPassword.setOnClickListener {
            if (_user.providerData[1].providerId == "google.com" || _user.providerData[1].providerId == "facebook.com") {
                Toast.makeText(
                    _view.context, "Não é possível trocar de senha com login social.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                updatePassword()
            }
        }

        _imgAddPhoto.setOnClickListener() {
            searchFile()
        }
    }

    private fun updatePassword() {
        _auth.sendPasswordResetEmail(_email)
            .addOnCompleteListener {
                Toast.makeText(
                    _view.context, "Um e-mail foi enviado para sua conta.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun searchFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        startActivityForResult(intent, CONTENT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTENT_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            _imageURI = data?.data
            _imgAddPhoto.setImageURI(_imageURI)
            updatePhoto()
        }
    }

    private fun updatePhoto() {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setPhotoUri(_imageURI).build()

        _auth.currentUser!!.updateProfile(profileUpdates)
    }
}