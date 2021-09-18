package com.cgmdigitalhouse.cinelist.account.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.cgmdigitalhouse.cinelist.MainActivity
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ChangeNameActivity : AppCompatActivity() {

    private lateinit var _edtName: TextInputLayout
    private  lateinit var _btnChangeName: Button
    private lateinit var _auth: FirebaseAuth
    private lateinit var _newName: String
    private lateinit var _currentUser: FirebaseUser
    private  lateinit var _btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_name)

        _edtName = findViewById(R.id.edt_nameChangeNameLayout)
        _btnChangeName = findViewById(R.id.btn_changeName)
        _auth = Firebase.auth
        _currentUser = _auth.currentUser!!
        _btnBack = findViewById(R.id.btn_BackChangeName)

        _btnChangeName.setOnClickListener {
            updateName()
        }

        _btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateName() {
        _newName = _edtName.editText?.text.toString()

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(_newName).build()

        _auth.currentUser!!.updateProfile(profileUpdates)

        Toast.makeText(this,getString(R.string.nome_alterado_sucesso), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}