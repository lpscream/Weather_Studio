package net.lpscream.weatherstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import net.lpscream.weatherstudio.fragments.LocationListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            startLocationList()
        }
    }

    private fun startLocationList() {
        val filmCatalougeFragment = LocationListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, filmCatalougeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}