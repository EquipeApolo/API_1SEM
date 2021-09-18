package com.cgmdigitalhouse.cinelist.account.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.cgmdigitalhouse.cinelist.MainActivity
import com.cgmdigitalhouse.cinelist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.oAuthCredential
import com.google.firebase.ktx.Firebase

class ChangeEmailActivity : AppCompatActivity() {

    private lateinit var _edtEmail: TextInputLayout
    private lateinit var _edtNewEmail: TextInputLayout
    private lateinit var _email: String
    private lateinit var _newEmail: String
    private  lateinit var _btnChangeEmail: Button
    private  lateinit var _btnBack: ImageView
    private lateinit var _auth: FirebaseAuth
    private lateinit var _currentUser: FirebaseUser

    private lateinit var _currentEmail: String
    private lateinit var  _currentPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        _edtEmail = findViewById(R.id.edt_emailChangeEmailLayout)
        _edtNewEmail = findViewById(R.id.edt_newEmailChangeEmailLayout)
        _btnChangeEmail = findViewById(R.id.btn_changeEmail)
        _btnBack = findViewById(R.id.btn_BackChangeEmail)
        _auth = Firebase.auth
        _currentUser = _auth.currentUser!!
        _currentEmail = _currentUser.email.toString()

        _btnChangeEmail.setOnClickListener {
            if (checkFields()) {
                updateEmail()
            }
        }

        _btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun checkFields(): Boolean {
        _email = _edtEmail.editText?.text.toString()
        _newEmail = _edtNewEmail.editText?.text.toString()

        _edtEmail.error = null
        _edtNewEmail.error = null
        var response = false

        when {
            _email.isEmpty() -> {
                _edtEmail.error = getString(R.string.preencher_email)
            }
            _newEmail.isEmpty() -> {
                _edtNewEmail.error = getString(R.string.preencher_email)
            }
            _email != _currentEmail -> {
                _edtEmail.error = getString(R.string.email_atual_errado)
            }
            _newEmail == _currentEmail -> {
                _edtNewEmail.error = getString(R.string.email_novo_errado)
            }
            else -> {
                response = true
            }
        }
        return response
    }

    private fun updateEmail() {
            _currentUser.updateEmail(_newEmail)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,getString(R.string.email_alterado_sucesso),Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w("Email", "Erro")
                    }
                }
    }
}