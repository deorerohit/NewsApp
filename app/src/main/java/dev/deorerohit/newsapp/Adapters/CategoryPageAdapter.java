package dev.deorerohit.newsapp.Adapters;

import android.icu.text.CaseMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CategoryPageAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> titleArrayList;


    public CategoryPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentArrayList = new ArrayList<>();
        this.titleArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }




    public void addFragments(Fragment fragment, String title) {
        fragmentArrayList.add(fragment);
        titleArrayList.add(title);
    }
}
