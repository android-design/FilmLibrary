package com.geekbrains.team.filmlibrary

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.team.filmlibrary.fragments.favoriteMovies.FavoriteMoviesFragment
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MainScreenFragment()
        val favoriteFragment = FavoriteMoviesFragment()
        transaction.replace(R.id.fragmentContainer, favoriteFragment)
        transaction.commit()

        btv.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                 R.id.navigation_favorite -> {
                    transaction.replace(R.id.fragmentContainer, favoriteFragment).commit()
                }
            }
        }
    }
}
