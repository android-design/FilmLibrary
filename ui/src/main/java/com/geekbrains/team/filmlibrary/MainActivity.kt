package com.geekbrains.team.filmlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.TestUpcomingMoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = TestUpcomingMoviesFragment()
        transaction.replace(R.id.fragmentContainer, fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
