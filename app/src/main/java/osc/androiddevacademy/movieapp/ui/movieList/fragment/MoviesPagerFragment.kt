package osc.androiddevacademy.movieapp.ui.movieList.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_pager.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.adapters.movieList.MoviePagerAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class MoviesPagerFragment : BaseFragment() {
    companion object {
        private const val PAGER_LIST_EXTRA = "list_extra"
        private const val PAGER_SELECTED_MOVIE_EXTRA = "selected_movie"
        fun getInstance(movieList: ArrayList<Movie>, selectedMovie: Movie): MoviesPagerFragment {
            val pagerFragment =
                MoviesPagerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PAGER_LIST_EXTRA, movieList)
            bundle.putParcelable(PAGER_SELECTED_MOVIE_EXTRA, selectedMovie)
            pagerFragment.arguments = bundle
            return pagerFragment
        }
    }

    private val movieList = mutableListOf<Movie>()
    private val pagerAdapter by lazy {
        MoviePagerAdapter(
            childFragmentManager
        )
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_pager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieList.addAll(arguments?.getParcelableArrayList(PAGER_LIST_EXTRA)!!)
        moviePager.adapter = pagerAdapter
        pagerAdapter.setMovies(movieList)
        moviePager.currentItem = movieList.indexOf(
            arguments?.getParcelable(PAGER_SELECTED_MOVIE_EXTRA)!!
        )
    }
}