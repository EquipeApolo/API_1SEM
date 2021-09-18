package com.cgmdigitalhouse.cinelist.moviedetails.details.repository

import android.content.Context

class SummaryRepository(private val context: Context) {
    val summary = "O astronauta Mark Watney (Matt Damon) é enviado a uma missão em Marte. Após uma severa tempestade ele é dado como morto, abandonado pelos colegas e acorda sozinho no misterioso planeta com escassos suprimentos, sem saber como reencontrar os companheiros ou retornar à Terra."


    fun getSummary(callback: (cast: String) -> Unit) {
        callback.invoke(summary)
    }
}