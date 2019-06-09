package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.common.RESPONSE_OK
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.movieDetails.MovieDetailsContract
import osc.androiddevacademy.movieapp.ui.movieList.fragment.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter(private val interactor: MovieInteractor): MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View

    override fun setView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun getReviews(movieId: Int) {
        interactor.getReviewsForMovie(movieId, reviewsCallback())
    }

    private fun reviewsCallback(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse> {
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(response: Response<ReviewsResponse>){
        response.body()?.reviews?.run {
            view.onGetReviewsSuccess(this)
        }
    }

    private fun handleSomethingWentWrong() = view.onGetReviewsFailed()
}