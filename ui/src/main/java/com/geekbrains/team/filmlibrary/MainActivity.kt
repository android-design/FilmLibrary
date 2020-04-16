package com.geekbrains.team.filmlibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.team.domain.movies.movieDetails.interactor.AddFavoriteMovieIdUseCase
import com.geekbrains.team.filmlibrary.fragments.favoriteMovies.FavoriteMoviesFragment
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

import com.geekbrains.team.filmlibrary.fragments.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = SearchFragment()
        transaction.replace(R.id.fragmentContainer, fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
