package osc.androiddevacademy.movieapp.ui.adapters.topRatedMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie

class TopMovieAdapter(): RecyclerView.Adapter<TopMovieHolder>() {

    private val data = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite_movie, parent, false)
        val viewHolder = TopMovieHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TopMovieHolder, position: Int) {
        val movie = data[position]
        holder.bind(movie)
    }

    fun setData(movieList: ArrayList<Movie>){
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }
}