package osc.androiddevacademy.movieapp.ui.adapters.topRatedMovies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_favourite_movie.view.*
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.model.Movie

class TopMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie){
        itemView.favouriteTitle.text = movie.title
        itemView.favouriteImage.loadImage(movie.poster)
    }
}