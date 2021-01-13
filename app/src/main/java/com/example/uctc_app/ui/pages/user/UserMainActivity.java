package com.example.uctc_app.ui.pages.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.ui.login.LoginFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {

    private NavController navController;
    private ProfileRepository repository;
    private BottomNavigationView navigationView;

    SharedPreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         repository = ProfileRepository.getInstance(helper.getAccessToken());
         repository.getUser().observe(this, observer );

        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(R.id.nav_homeUser, R.id.nav_programUser, R.id.nav_myProgramUser, R.id.nav_profile)
                .build();

        navController = Navigation.findNavController(this, R.id.fragmentcontainer);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer_staff);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            Log.d("USER ROLE", "hai rayyyyyyyyyyyyyyyyyyyyy");
            NavDirections action;
            if (user.getRole_id().equalsIgnoreCase("1")) {
                Log.d("USER ROLE", "ADMIIIN");

                navigationView = findViewById(R.id.bottom_nav_admin);
                navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                    if (destination.getId() == R.id.nav_home_admin || destination.getId() == R.id.nav_finance_report_admin || destination.getId() == R.id.nav_program_list_admin
                            || destination.getId() == R.id.nav_proposal_admin || destination.getId() == R.id.nav_user_list_admin){
                        navigationView.setVisibility(View.VISIBLE);
                    }else{
                        navigationView.setVisibility(View.GONE);
                    }
                });

            } else if (user.getRole_id().equalsIgnoreCase("2")) {
                Log.d("USER ROLE", "STAFF");

                navigationView = findViewById(R.id.bottom_nav_staff);
                navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                    if (destination.getId() == R.id.nav_home_staff || destination.getId() == R.id.nav_program_staff || destination.getId() == R.id.nav_action_plan_staff){
                        navigationView.setVisibility(View.VISIBLE);
                    }else{
                        navigationView.setVisibility(View.GONE);
                    }
                });

            } else {

                Log.d("USER ROLE", "USER");
                navigationView = findViewById(R.id.bottom_nav_user);
                navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                    if (destination.getId() == R.id.nav_homeUser || destination.getId() == R.id.nav_programUser || destination.getId() == R.id.nav_myProgramUser || destination.getId() == R.id.nav_profile){
                        navigationView.setVisibility(View.VISIBLE);
                    }else{
                        navigationView.setVisibility(View.GONE);
                    }
                });

            }
        }
    };
}