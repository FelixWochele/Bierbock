package com.bierbock;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.Menu;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import com.bierbock.databinding.ActivityMainBinding;

import java.io.InputStream;

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
                R.id.homeFragment, R.layout.fragment_challenge, R.id.heatMapFragment, R.id.action_settings
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

            //Fragment one = new ChallengeFragment();
            //Fragment two = new HomeFragment();
            //Fragment three = new HeatMapFragment();
            //Fragment maps = new MapsFragment();

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

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
    }

    //TODO: Check, if needed, maybe a simple load method is enough
    //Private class to load the images needed for the beer history?
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}


    /*public static void animateBubble(Context context, ImageView bubble) {
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator anim = ObjectAnimator.ofFloat(bubble, "translationY", 0, -screenHeight);
        anim.setDuration(2000); // Adjust duration as desired
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                anim.start();
            }
        });
        anim.start();
    } */


    // private NavController navController;
    //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
    //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        System.out.println("ID: " + id);


        return super.onOptionsItemSelected(item);
    }
    */





/*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    } */
//NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
//navController = navHostFragment.getNavController();

//navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

//NavigationUI.setupWithNavController(binding.bottomAppBar, navController);

        /*navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                String title="";
                switch(navDestination.getId()){
                    case R.id.challangeFragment:
                        title = "Challenges";
                        break;
                    case R.id.heatMapFragment:
                        title = "Heatmap";
                        break;
                    case R.id.homeFragment:
                        title = "Home";
                        break;
                }
                binding.toolbar.setTitle(title);
            }
        }); */
