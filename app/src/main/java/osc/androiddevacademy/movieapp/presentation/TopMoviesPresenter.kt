package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.common.RESPONSE_OK
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.topRatedList.TopRatedMoviesContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopMoviesPresenter(private val interactor: MovieInteractor): TopRatedMoviesContract.Presenter {

    private lateinit var view: TopRatedMoviesContract.View

    override fun setView(view: TopRatedMoviesContract.View) {
        this.view = view
    }

    override fun getTopRatedMovies() {
        interactor.getTopMovies(popularMoviesCallback())
    }

    private fun popularMoviesCallback(): Callback<MoviesResponse> =
        object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                view.onGetTopMoviesFailed()
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

    private fun handleOkResponse(response: Response<MoviesResponse>){
        response.body()?.movies?.run {
            view.onTopMoviesReceived(this)
        }
    }

    private fun handleSomethingWentWrong() = view.onGetTopMoviesFailed()
}