package osc.androiddevacademy.movieapp.ui.movieList.fragment

import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridContract {
    interface View{
        fun onMovieListRecieved(movieList: ArrayList<Movie>)
        fun onGetMovieListFailed()
        fun onSetFavouriteSuccess()
        fun onDeleteFavouriteSuccess()
    }

    interface Presenter{
        fun setView(view: MoviesGridContract.View)
        fun getAllMovies()
        fun setFavourite(movie: Movie)
        fun deleteFavourite(movie: Movie)
    }
}