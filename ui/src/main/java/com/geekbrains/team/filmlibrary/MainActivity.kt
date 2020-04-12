package com.geekbrains.team.filmlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MainScreenFragment()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}
