package osc.androiddevacademy.movieapp.ui.favouriteMovieList

import osc.androiddevacademy.movieapp.model.Movie

interface FavouritesFragmentContract {

    interface View{
        fun onFavMoviesReceived(movieList: List<Movie>)
    }

    interface Presenter{
        fun setView(view: View)
        fun getFavMovies()
    }
}