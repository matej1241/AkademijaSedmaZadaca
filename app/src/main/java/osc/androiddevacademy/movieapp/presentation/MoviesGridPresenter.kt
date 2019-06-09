package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.common.RESPONSE_OK
import osc.androiddevacademy.movieapp.database.MoviesDao
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.movieList.fragment.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridPresenter(private val interactor: MovieInteractor, private val appDatabase: MoviesDatabase): MoviesGridContract.Presenter {

    private lateinit var view: MoviesGridContract.View

    override fun setView(view: MoviesGridContract.View) {
        this.view = view
    }

    override fun getAllMovies() {
        interactor.getPopularMovies(popularMoviesCallback())
    }

    private fun popularMoviesCallback(): Callback<MoviesResponse> =
        object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                view.onGetMovieListFailed()
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    when (response.code()) {
                        RESPONSE_OK -> handleOkResponse(response)
                        else -> handleSomethingWentWrong()
                    }
                }
            }
        }

    override fun setFavourite(movie: Movie){
        appDatabase.moviesDao().addFavoriteMovie(movie)
        handleFavouritesOk()
    }

    override fun deleteFavourite(movie: Movie){
        appDatabase.moviesDao().deleteFavoriteMovie(movie)
        handleDeleteOk()
    }

    private fun getFavouriteMovies(): List<Movie>{
        return appDatabase.moviesDao().getFavoriteMovies()
    }

    private fun handleOkResponse(response: Response<MoviesResponse>){
        val responseList = response.body()?.movies
        val favourites = getFavouriteMovies()
        for (movie in responseList!!){
            for (favMovie in favourites){
                if (movie.title == favMovie.title) movie.isFavorite = true
            }
        }
        view.onMovieListRecieved(responseList)
    }

    private fun handleSomethingWentWrong() = view.onGetMovieListFailed()

    private fun handleFavouritesOk() = view.onSetFavouriteSuccess()

    private fun handleDeleteOk() = view.onDeleteFavouriteSuccess()
}