package com.geekbrains.team.filmlibrary

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.geekbrains.team.data.movies.favoriteMovies.repository.FavoriteMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.favoriteMovies.interactor.AddFavoriteMovieIdUseCase
import com.geekbrains.team.filmlibrary.Const.MIN_LUX
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.OnLikeClickListener
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), OnItemSelectedListener{

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setUpNavigation()
        setSensor()
    }

    private fun setUpNavigation() {
        NavigationUI.setupWithNavController(btv, navController)
    }

    private fun setSensor() {
        val mySensorManager =
            getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val lightSensor: Sensor? = mySensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        lightSensor?.let {
            mySensorManager.registerListener(
                lightSensorListener,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    private val lightSensorListener: SensorEventListener =
        object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            }

            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == Sensor.TYPE_LIGHT) {
                    val currentLux = event.values.first()
                    if (currentLux < MIN_LUX && AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        recreate()
                    } else if (currentLux > MIN_LUX && AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        recreate()
                    }
                }
            }
        }

    override fun openMovieDetails(id: Int) {
        val directions = MainScreenFragmentDirections.navigateToMovieDetails(id)
        navController.navigate(directions)
    }

    override fun onLikeClick(id: Int) {
        Toast.makeText(this, "Ha-Ha", Toast.LENGTH_SHORT).show()
    }

}