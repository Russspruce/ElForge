package com.epicodus.andrewrusso.elforge.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.ui.GameDetailFragment;

import java.util.ArrayList;

/**
 * Created by andrewrusso on 7/23/16.
 */
public class GamePagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Game> mGames;

    public GamePagerAdapter(FragmentManager fm, ArrayList<Game> game) {
        super(fm);
        mGames = game;
    }

    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mGames.get(position));
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGames.get(position).getName();
    }

}
