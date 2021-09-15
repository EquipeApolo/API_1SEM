package com.cgmdigitalhouse.cinelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var _name: String
    private lateinit var _email: String
    private lateinit var _password: String
    private lateinit var _passwordConfirm: String
    private lateinit var _auth: FirebaseAuth
    private lateinit var _btnSignUp: Button
    private lateinit var _edtSignUpName: TextInputLayout
    private lateinit var _edtSignUpEmail: TextInputLayout
    private lateinit var _edtSignUpPassword: TextInputLayout
    private lateinit var _edtSignUpConfirmPassword: TextInputLayout
    private lateinit var _movieListViewModel: MovieListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        _movieListViewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(
                MovieListRepository(
                    AppDatabase.getDatabase(
                        this
                    ).listMovieDao()
                )
            )
        ).get(MovieListViewModel::class.java)

        // Variaveis
        _auth = Firebase.auth

        _btnSignUp = findViewById(R.id.btn_signUp)
        _edtSignUpName = findViewById(R.id.edt_singupNomeLayout)
        _edtSignUpEmail = findViewById(R.id.edt_singupEmailLayout)
        _edtSignUpPassword = findViewById(R.id.edt_singupPasswordLayout)
        _edtSignUpConfirmPassword = findViewById(R.id.edt_singupPasswordConfirmacaoLayout)

        _btnSignUp.setOnClickListener {
            if (checkFields()) {
                createAccount()
            }
        }
    }

    private fun checkFields(): Boolean {
        _name = _edtSignUpName.editText!!.text.toString().trim()
        _email = _edtSignUpEmail.editText!!.text.toString().trim()
        _password = _edtSignUpPassword.editText!!.text.toString().trim()
        _passwordConfirm = _edtSignUpConfirmPassword.editText!!.text.toString().trim()

        _edtSignUpName.error = null
        _edtSignUpEmail.error = null
        _edtSignUpPassword.error = null
        _edtSignUpConfirmPassword.error = null
        var response = false
        when {
            _name.isEmpty() -> {
                _edtSignUpName.error = getString(R.string.preencher_nome)
            }
            _email.isEmpty() -> {
                _edtSignUpEmail.error = getString(R.string.preencher_email)
            }
            _password.isEmpty() -> {
                _edtSignUpPassword.error = getString(R.string.preencher_senha)
            }
            _password != _passwordConfirm -> {
                _edtSignUpConfirmPassword.error = getString(R.string.preencher_confirmar_senha)
            }
            else -> {
                response = true
            }
        }
        return response
    }

    private fun createAccount() {
        _auth.createUserWithEmailAndPassword(_email, _password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(_name).build()

                    _auth.currentUser!!.updateProfile(profileUpdates)

                    FirebaseAuth.getInstance().uid?.let { userId ->
                        _movieListViewModel.searchWatchList(userId).observe(this, Observer{
                            createWatchList(it[0].toInt(), userId)
                        })
                    }

                    Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.usuario_sucesso),
                        Snackbar.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.apply {
                        putExtra("provider", ProviderType.BASIC)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.usuario_erro),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun createWatchList(count: Int, id:String){
        if(count == 0){
            _movieListViewModel.inserirListMovie("WatchList","Filmes que pretendo assistir","",id,true).observe(this, Observer {
            })
        }
    }
}