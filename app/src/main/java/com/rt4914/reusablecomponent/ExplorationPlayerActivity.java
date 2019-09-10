package com.rt4914.reusablecomponent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ExplorationPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration_player);
        setFragment();
    }

    private void setFragment() {
        ExplorationPlayerFragment explorationPlayerFragment = new ExplorationPlayerFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flRoot, explorationPlayerFragment);
        transaction.commit();
    }
}
