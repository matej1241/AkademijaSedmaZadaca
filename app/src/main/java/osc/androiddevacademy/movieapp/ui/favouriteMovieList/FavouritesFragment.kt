package osc.androiddevacademy.movieapp.ui.favouriteMovieList


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favourites.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.presentation.FavouriteMoviesPresenter
import osc.androiddevacademy.movieapp.ui.adapters.favouriteMovies.FavouriteMovieAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class FavouritesFragment : BaseFragment(), FavouritesFragmentContract.View {
    override fun getLayoutResourceId(): Int = R.layout.fragment_favourites

    companion object {

        fun getInstance(): FavouritesFragment {
            return FavouritesFragment()
        }
    }
    private val favouriteMovieAdapter by lazy {
        FavouriteMovieAdapter()
    }
    private val presenter by lazy {
        FavouriteMoviesPresenter(MoviesDatabase.getInstance(App.getAppContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        favouriteList.apply {
            adapter = favouriteMovieAdapter
            layoutManager = LinearLayoutManager(App.getAppContext())
        }
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        presenter.getFavMovies()
    }

    override fun onFavMoviesReceived(movieList: List<Movie>) {
        favouriteMovieAdapter.setData(movieList)
    }
}
