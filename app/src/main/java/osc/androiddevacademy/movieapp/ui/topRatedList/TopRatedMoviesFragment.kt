package osc.androiddevacademy.movieapp.ui.topRatedList


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.TopMoviesPresenter
import osc.androiddevacademy.movieapp.ui.adapters.topRatedMovies.TopMovieAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment


class TopRatedMoviesFragment : BaseFragment(), TopRatedMoviesContract.View {
    override fun getLayoutResourceId(): Int = R.layout.fragment_top_rated_movies

    companion object {

        fun getInstance(): TopRatedMoviesFragment {
            return TopRatedMoviesFragment()
        }
    }
    private val topMovieAdapter by lazy {
        TopMovieAdapter()
    }
    private val presenter by lazy { TopMoviesPresenter(BackendFactory.getMovieInteractor()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        topRatedList.apply {
            adapter = topMovieAdapter
            layoutManager = LinearLayoutManager(App.getAppContext())
        }
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        presenter.getTopRatedMovies()
    }

    override fun onTopMoviesReceived(movieList: ArrayList<Movie>) = topMovieAdapter.setData(movieList)

    override fun onGetTopMoviesFailed() {
        Log.d("Tag", "Failed")
    }
}
