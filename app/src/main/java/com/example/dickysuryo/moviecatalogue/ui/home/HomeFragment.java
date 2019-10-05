package com.example.dickysuryo.moviecatalogue.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.dickysuryo.moviecatalogue.Adapter.ViewPagerAdapter;
import com.example.dickysuryo.moviecatalogue.Fragment.MovieListFragment;
import com.example.dickysuryo.moviecatalogue.Fragment.MoviePopularFragment;
import com.example.dickysuryo.moviecatalogue.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getFragmentManager());
        adapter.addFragment(new MovieListFragment(),getResources().getString(R.string.TAB_TV_SHOW));
        adapter.addFragment(new MoviePopularFragment(), getResources().getString(R.string.TAB_TERPOPULER));
        viewPager.setAdapter(adapter);

    }
}