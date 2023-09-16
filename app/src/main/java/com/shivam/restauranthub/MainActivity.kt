package com.shivam.restauranthub

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var frameLayout: FrameLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var toolbars: Toolbar
    private lateinit var navigationBar: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar = findViewById(R.id.navigation)
        frameLayout = findViewById(R.id.framelayout)
        coordinatorLayout = findViewById(R.id.coordinator)
        toolbars = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer)

        //  supportFragmentManager.beginTransaction().remove(R.id.framelayout)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, HomeFragment()).commit()
        supportActionBar?.title = "Restaurant Dashboard"

        setSupportActionBar(toolbars)
        supportActionBar?.title = "Restaurant Hub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawer,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawer.addDrawerListener(actionBarDrawerToggle)



        actionBarDrawerToggle.syncState()


        navigationBar.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(this@MainActivity, "Home", Toast.LENGTH_SHORT)
                        .show()
                    supportFragmentManager.beginTransaction().addToBackStack("Dashboard")
                        .replace(R.id.framelayout, HomeFragment()).commit()
                    supportActionBar?.title = "Restaurant Dashboard"
                }
                R.id.favouriterestaurants -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Favourite",
                        Toast.LENGTH_SHORT
                    ).show()
                    supportFragmentManager.beginTransaction()
                        .addToBackStack("Favourites Restaurants")
                        .replace(R.id.framelayout, FavouriteFragment()).commit()
                    supportActionBar?.title = "Favourites"
                }
                R.id.profile -> {
                    Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT)
                        .show()

                    supportFragmentManager.beginTransaction().addToBackStack("Profile")
                        .replace(R.id.framelayout, ProfileFragment()).commit()
                    supportActionBar?.title = "My Profile"

                }
                R.id.faqs -> {
                    Toast.makeText(this@MainActivity, "FAQs", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().addToBackStack("About")
                        .replace(R.id.framelayout, FaqsFragment()).commit()
                    supportActionBar?.title = "FAQs"
                }
                R.id.logout-> {
                    Toast.makeText(this@MainActivity, "Fake Dummy Logout Successfully...", Toast.LENGTH_SHORT).show()

                }
            }
            drawer.closeDrawers()

            return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }


}






