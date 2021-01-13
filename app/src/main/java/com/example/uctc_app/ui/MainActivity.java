package com.example.uctc_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity { //ini dipake
    SharedPreferenceHelper helper;

    private NavController navController;
    private ProfileRepository repository;
    private BottomNavigationView navigationViewAdmin, navigationViewStaff, navigationViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Hello","in Main Menu");

        helper = SharedPreferenceHelper.getInstance(getApplicationContext());
        navigationViewAdmin = findViewById(R.id.bottom_nav_admin);
        navigationViewStaff = findViewById(R.id.bottom_nav_staff);
        navigationViewUser= findViewById(R.id.bottom_nav_user);

        if(!helper.getAccessToken().isEmpty()){
            repository = ProfileRepository.getInstance(helper.getAccessToken());
            repository.getUser().observe(this, observer );

//            NavigationUI.setupWithNavController(navigationView, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null);
    }

    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            Log.d("USER ROLE", "hai rayyyyyyyyyyyyyyyyyyyyy");
            NavDirections action;
            if (user.getRole_id().equalsIgnoreCase("1")) {
                Log.d("USER ROLE", "ADMIIIN");
                AppBarConfiguration configuration = new AppBarConfiguration
                        .Builder(R.id.nav_home_admin,  R.id.nav_finance_report_admin, R.id.nav_program_staff, R.id.nav_proposal_admin, R.id.nav_user_list_admin)
                        .build();

                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer);
                navController = navHostFragment.getNavController();

                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, configuration);
                NavigationUI.setupWithNavController(navigationViewAdmin, navController);
                navigationViewStaff.setVisibility(View.GONE);
                navigationViewUser.setVisibility(View.GONE);

            } else if (user.getRole_id().equalsIgnoreCase("2")) {
                Log.d("USER ROLE", "STAFF");

                AppBarConfiguration configuration = new AppBarConfiguration
                        .Builder(R.id.nav_home_staff,  R.id.nav_program_staff, R.id.nav_action_plan_staff)
                        .build();

                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer);
                navController = navHostFragment.getNavController();

                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, configuration);
                NavigationUI.setupWithNavController(navigationViewStaff, navController);
                navigationViewAdmin.setVisibility(View.GONE);
                navigationViewUser.setVisibility(View.GONE);

            } else if (user.getRole_id().equalsIgnoreCase("3")) {

                AppBarConfiguration configuration = new AppBarConfiguration
                        .Builder(R.id.nav_homeUser,  R.id.nav_programUser, R.id.nav_myProgramUser, R.id.nav_profile)
                        .build();

                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer);
                navController = navHostFragment.getNavController();

                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, configuration);
                NavigationUI.setupWithNavController(navigationViewUser, navController);
                navigationViewAdmin.setVisibility(View.GONE);
                navigationViewStaff.setVisibility(View.GONE);

            }
        }
    };
}