package data.extentions

import com.geekbrains.team.domain.model.Movie
import data.movie.MovieModel
import data.movie.TopRatedMovies

fun MovieModel.toMovie(movieModel: MovieModel): Movie {
    return Movie(
        id = this.id,
        genres =
    )
}

fun TopRatedMovies.toMovie() {
    val movieList = this.result
}