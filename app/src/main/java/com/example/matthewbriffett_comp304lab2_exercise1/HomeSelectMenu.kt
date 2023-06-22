package com.example.matthewbriffett_comp304lab2_exercise1

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.matthewbriffett_comp304lab2_exercise1.databinding.ActivityHomeSelectMenuBinding
import com.google.android.material.navigation.NavigationView

class HomeSelectMenu : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeSelectMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set up view binding
        binding = ActivityHomeSelectMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up the toolbar
        setSupportActionBar(binding.appBarHomeSelectMenu.toolbar)

        //Get the NavController for navigation
        val navController = findNavController(R.id.nav_host_fragment_content_home_select_menu)

        //Set initial OnClickListener for the button
        binding.appBarHomeSelectMenu.fab.setOnClickListener { view ->
            navController.navigate(R.id.nav_selected_homes)
        }

        //Listen for changes in the current destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            //Update the OnClickListener of the button based on the current destination
            when (destination.id) {
                R.id.nav_home -> {
                   binding.appBarHomeSelectMenu.fab.visibility = View.INVISIBLE
                }
            }
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView


        //Set up the AppBarConfiguration with top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_apartment, R.id.nav_condo_apartment, R.id.nav_semi_detached_home, R.id.nav_detached_home, R.id.nav_town_house
            ), drawerLayout
        )

        //Set up the action bar with the NavController and AppBarConfiguration
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Set up the NavigationView with the NavController
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_select_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        //Handle up navigation using the NavController and AppBarConfiguration
        val navController = findNavController(R.id.nav_host_fragment_content_home_select_menu)
        //manually reset up onlicklistener on navigate up to prevent navigation issues with fab
        binding.appBarHomeSelectMenu.fab.setOnClickListener { view ->
            navController.navigate(R.id.nav_selected_homes)
        }
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
