package com.geekbrains.team.filmlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragment
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
