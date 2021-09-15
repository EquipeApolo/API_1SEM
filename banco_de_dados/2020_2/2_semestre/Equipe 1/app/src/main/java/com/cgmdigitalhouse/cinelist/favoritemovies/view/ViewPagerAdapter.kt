package com.cgmdigitalhouse.cinelist.favoritemovies.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view.MovieListFragment
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.view.WatchlistFragment

class ViewPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(
    fragment
) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = if(position==0) {
        MovieListFragment()
    } else {
        WatchlistFragment()
    }


}
