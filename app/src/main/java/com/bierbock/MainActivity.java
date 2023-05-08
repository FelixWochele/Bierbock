package com.bierbock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.bierbock.databinding.ActivityMainBinding;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.layout.fragment_challenge, R.id.heatmap, R.id.user_profile
        ).build();

        //Make a photo button:
        binding.fab.setOnClickListener(e -> {
            //AsyncTask be = new Backend().execute();
            startActivity(new Intent(this, ScanActivity.class));
            finish();
        });

        // Set the Home item as selected on the start of the application
        binding.bottomNavigationView.setSelectedItemId(R.id.homeMenue);

        binding.bottomNavigationView.setOnItemSelectedListener(e -> {
            System.out.println("TEEEEEEEEEEEST2");
            switch (e.getItemId()) {
                case R.id.challanges:
                    System.out.println("Test1");
                    binding.toolbar.setTitle("Challenges");
                    replaceFragment(new ChallengeFragment());
                    break;
                case R.id.homeMenue:
                    System.out.println("Test2");
                    binding.toolbar.setTitle("Home");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.heatmap:
                    System.out.println("Test3");
                    binding.toolbar.setTitle("Heat map");
                    replaceFragment(new MapsFragment());
                    break;

            }
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_profile:
                Intent i = new Intent(this,UserProfileActivity.class);
                this.startActivity(i);
                return true;
            case R.id.logout:

                //Set the token to an empty String to not get automatic logged in
                SharedPreferences sharedPreferences = null;
                try {
                    sharedPreferences = EncryptedSharedPreferences.create(
                            "authentication_key",
                            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                            getApplicationContext(),
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
                } catch (GeneralSecurityException | IOException e) {
                    throw new RuntimeException(e);
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token_key", "");
                editor.apply();

                Intent j = new Intent(this,LoginActivity.class);
                this.startActivity(j);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
    }

}