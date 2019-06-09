package osc.androiddevacademy.movieapp.ui.topRatedList

import android.view.View
import osc.androiddevacademy.movieapp.model.Movie

interface TopRatedMoviesContract {

    interface View{
        fun onTopMoviesReceived(movieList: ArrayList<Movie>)
        fun onGetTopMoviesFailed()
    }

    interface Presenter{
        fun setView(view: View)
        fun getTopRatedMovies()
    }
}