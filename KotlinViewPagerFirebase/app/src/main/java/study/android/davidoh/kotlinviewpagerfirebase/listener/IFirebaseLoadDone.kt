package study.android.davidoh.kotlinviewpagerfirebase.listener

import study.android.davidoh.kotlinviewpagerfirebase.model.Movie

interface IFirebaseLoadDone {
    fun onMovieLoadSuccess(movieList: List<Movie>)
    fun onMovieLoadFailed(message: String)
}