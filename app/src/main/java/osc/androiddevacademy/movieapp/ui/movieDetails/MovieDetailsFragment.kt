package osc.androiddevacademy.movieapp.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragemnt_details.*
import kotlinx.android.synthetic.main.fragment_pager.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.MovieDetailsPresenter
import osc.androiddevacademy.movieapp.ui.adapters.movieDetails.ReviewAdapter
import osc.androiddevacademy.movieapp.ui.adapters.movieList.MoviePagerAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment
import osc.androiddevacademy.movieapp.ui.movieList.fragment.MoviesPagerFragment

class MovieDetailsFragment : BaseFragment(), MovieDetailsContract.View {
    private val reviewsAdapter by lazy { ReviewAdapter() }

    private val presenter: MovieDetailsContract.Presenter by lazy { MovieDetailsPresenter(BackendFactory.getMovieInteractor()) }
    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }
    companion object {
        private const val MOVIE_EXTRA = "movie_extra"

        fun getInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_EXTRA, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var movie: Movie
    override fun getLayoutResourceId(): Int = R.layout.fragemnt_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(MOVIE_EXTRA) as Movie
        presenter.setView(this)
        initUi()
        getReviews()
        initListeners()
    }

    private fun initUi(){
        movieImage.loadImage(movie.poster)
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = movie.releaseDate
        movieVoteAverage.text = movie.averageVote.toString()

        movieReviewList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners(){
        movieFavorite.setOnClickListener {
            onFavoriteClicked()
        }
    }

    private fun onFavoriteClicked(){

    }

    private fun getReviews(){
        presenter.getReviews(movie.id)
    }


    override fun onGetReviewsSuccess(reviews : List<Review>) {
        reviewsAdapter.setReviewList(reviews)
    }

    override fun onGetReviewsFailed() {
        Log.d("Tag", "Failed")
    }
}