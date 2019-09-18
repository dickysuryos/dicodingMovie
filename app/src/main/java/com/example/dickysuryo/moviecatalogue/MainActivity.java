package com.example.dickysuryo.moviecatalogue;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dickysuryo.moviecatalogue.Adapter.ViewPagerAdapter;
import com.example.dickysuryo.moviecatalogue.Fragment.MovieListFragment;
import com.example.dickysuryo.moviecatalogue.Fragment.MoviePopularFragment;




public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
      setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieListFragment(),getResources().getString(R.string.TAB_TV_SHOW));
        adapter.addFragment(new MoviePopularFragment(), getResources().getString(R.string.TAB_TERPOPULER));
        viewPager.setAdapter(adapter);

    }
    private void loadViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment((MovieListFragment)getSupportFragmentManager().findFragmentByTag("newest"),getResources().getString(R.string.TAB_TV_SHOW));
        adapter.addFragment((MoviePopularFragment)getSupportFragmentManager().findFragmentByTag("popular"), getResources().getString(R.string.TAB_TERPOPULER));
        viewPager.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
