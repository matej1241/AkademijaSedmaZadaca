package osc.androiddevacademy.movieapp.ui.movieDetails

import osc.androiddevacademy.movieapp.model.Review

interface MovieDetailsContract {
    interface View{
        fun onGetReviewsSuccess(reviews : List<Review>)
        fun onGetReviewsFailed()
    }

    interface Presenter{
        fun setView(view: MovieDetailsContract.View)
        fun getReviews(movieId: Int)
    }
}