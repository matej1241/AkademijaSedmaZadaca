package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.favouriteMovieList.FavouritesFragmentContract

class FavouriteMoviesPresenter(private val appDatabase: MoviesDatabase): FavouritesFragmentContract.Presenter {

    private lateinit var view: FavouritesFragmentContract.View

    override fun setView(view: FavouritesFragmentContract.View) {
        this.view = view
    }

    override fun getFavMovies() {
        val favMovies = appDatabase.moviesDao().getFavoriteMovies()
        handleFavMovies(favMovies)
    }

    private fun handleFavMovies(movieList: List<Movie>) = view.onFavMoviesReceived(movieList)

}