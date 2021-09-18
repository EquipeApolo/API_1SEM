package com.cgmdigitalhouse.cinelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var _auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // ProgressBar
        val maxSplashTime: Long = 3000
        var progressSplash: Long = 0
        val percentResult = maxSplashTime / 101
        val progressSplashBar = findViewById<ProgressBar>(R.id.progressSplashBar)

        for(x in 0..100){
            Handler().postDelayed({
                progressSplashBar.progress = x
            }, progressSplash)
            progressSplash = progressSplash + percentResult
        }

        Handler().postDelayed({
            _auth = Firebase.auth
            val currentUser = _auth.currentUser

            if(currentUser == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        },maxSplashTime)

    }

}