/**
 * 
 */
package com.cgamble.games.towersofhanoi;

import towers.Tower;
import android.app.Fragment;
import android.os.Bundle;

/**
 * @author $hadow$torm
 *
 */
public class TowerFragment extends Fragment {
    
    private Tower tower;
    
    /**
     * Creates the UI for a single tower.
     */
    public TowerFragment(Tower t) {
        // TODO Auto-generated constructor stub
        tower = t;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    public void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    public void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    public void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // The activity is about to be destroyed.
    }
    
    public void onClick() {
        
    }
    
    public void update() {}
    
}
