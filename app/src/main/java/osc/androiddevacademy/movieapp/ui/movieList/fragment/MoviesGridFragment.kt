package osc.androiddevacademy.movieapp.ui.movieList.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_details.*
import kotlinx.android.synthetic.main.fragemnt_details.movieFavorite
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import kotlinx.android.synthetic.main.item_movie.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.MoviesGridPresenter
import osc.androiddevacademy.movieapp.ui.adapters.movieList.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class MoviesGridFragment : BaseFragment(), MoviesGridContract.View {

    private val SPAN_COUNT = 2
    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }
    private val presenter: MoviesGridContract.Presenter by lazy {
        MoviesGridPresenter(
            BackendFactory.getMovieInteractor(),
            MoviesDatabase.getInstance((App.getAppContext()))
        )
    }
    private val movieList = arrayListOf<Movie>()

    override fun getLayoutResourceId(): Int = R.layout.fragemnt_movie_grid

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }
        requestPopularMovies()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    private fun requestPopularMovies() {
        presenter.getAllMovies()
    }

    override fun onMovieListRecieved(movies: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        gridAdapter.setMovies(this.movieList)
    }

    override fun onGetMovieListFailed() {
      Log.d("Tag", "Failed")
    }

    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(
                movieList,
                movie
            ),
            true
        )
    }

    private fun onFavoriteClicked(movie: Movie) {
        when(movie.isFavorite){
            false -> presenter.setFavourite(movie)
            true -> presenter.deleteFavourite(movie)
        }
    }

    override fun onSetFavouriteSuccess() {
        movieFavorite.setImageResource(R.drawable.ic_favorite_full)
    }

    override fun onDeleteFavouriteSuccess(){
        movieFavorite.setImageResource(R.drawable.ic_favorite_empty)
    }
}