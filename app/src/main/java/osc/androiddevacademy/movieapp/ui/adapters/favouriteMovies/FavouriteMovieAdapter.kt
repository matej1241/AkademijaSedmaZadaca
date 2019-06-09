package osc.androiddevacademy.movieapp.ui.adapters.favouriteMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie

class FavouriteMovieAdapter(): RecyclerView.Adapter<FavouriteMovieHolder>() {

    private val data = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite_movie, parent, false)
        val viewHolder = FavouriteMovieHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FavouriteMovieHolder, position: Int) {
        val movie = data[position]
        holder.bind(movie)
    }

    fun setData(movieList: List<Movie>){
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }
}