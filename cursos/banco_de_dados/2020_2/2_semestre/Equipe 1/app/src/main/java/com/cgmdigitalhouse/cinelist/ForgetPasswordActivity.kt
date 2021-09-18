package com.cgmdigitalhouse.cinelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var _btnChangePassword: Button
    private lateinit var _edtEmailInput: TextInputEditText
    private lateinit var _email: String
    private lateinit var _auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        _btnChangePassword = findViewById(R.id.btn_changePassword)
        _edtEmailInput = findViewById(R.id.edt_emailChangePasswordInput)
        _auth = Firebase.auth

        _btnChangePassword.setOnClickListener {
            forgetPassword()
        }
    }

    private fun forgetPassword() {
        _email = _edtEmailInput.text.toString()
        _auth.sendPasswordResetEmail(_email)
            .addOnCompleteListener {
                Toast.makeText(
                    this, "Um e-mail foi enviado para sua conta.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}