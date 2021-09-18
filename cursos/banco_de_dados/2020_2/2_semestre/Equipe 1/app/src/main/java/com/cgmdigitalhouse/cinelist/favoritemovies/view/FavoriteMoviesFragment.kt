package com.cgmdigitalhouse.cinelist.favoritemovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.cgmdigitalhouse.cinelist.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteMoviesFragment : Fragment() {

    private lateinit var myView: View
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_favorite_movies, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager = myView.findViewById(R.id.vpMovieList_movieListFragment)
        viewPager.isSaveEnabled = false
        viewPager.adapter = viewPagerAdapter

        val tabLayout: TabLayout = myView.findViewById(R.id.tabs_movieListFragment)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) {
                getString(R.string.tab_listas)
            } else {
                getString(R.string.tab_watchlist)
            }

        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteMoviesFragment()
    }
}