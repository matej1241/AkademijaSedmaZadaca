package osc.androiddevacademy.movieapp.ui.movieList

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_movies.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.ui.favouriteMovieList.FavouritesFragment
import osc.androiddevacademy.movieapp.ui.topRatedList.TopRatedMoviesFragment
import osc.androiddevacademy.movieapp.ui.base.BaseActivity
import osc.androiddevacademy.movieapp.ui.movieList.fragment.MoviesGridFragment

class MoviesActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int = R.layout.activity_movies

    private val onNavItemSelected  = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.movies -> {
                showFragment(MoviesGridFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.favourites -> {
                showFragment(FavouritesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.topRated -> {
                showFragment(TopRatedMoviesFragment())
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    override fun setUpUi() {
        showFragment(MoviesGridFragment())
        bottomNavigation.setOnNavigationItemSelectedListener(onNavItemSelected)
    }
}
