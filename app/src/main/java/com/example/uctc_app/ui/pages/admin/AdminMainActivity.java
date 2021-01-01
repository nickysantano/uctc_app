package com.example.uctc_app.ui.pages.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.uctc_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Log.d("Hello","in Main Menu");
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_admin);

        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(R.id.nav_home_admin, R.id.nav_finance_report_admin, R.id.nav_program_list_admin, R.id.nav_proposal_admin, R.id.nav_user_list_admin)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer_admin);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_home_admin || destination.getId() == R.id.nav_finance_report_admin || destination.getId() == R.id.nav_program_list_admin
                    || destination.getId() == R.id.nav_proposal_admin || destination.getId() == R.id.nav_user_list_admin){
                navigationView.setVisibility(View.VISIBLE);
            }else{
                navigationView.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null);
    }
}