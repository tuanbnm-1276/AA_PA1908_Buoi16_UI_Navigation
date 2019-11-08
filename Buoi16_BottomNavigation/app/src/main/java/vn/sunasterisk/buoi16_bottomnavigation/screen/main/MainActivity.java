package vn.sunasterisk.buoi16_bottomnavigation.screen.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.sunasterisk.buoi16_bottomnavigation.R;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.FavoriteFragment;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.MusicFragment;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.SettingFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private ActionBar mActionBar;

    private MusicFragment mMusicFragment;
    private FavoriteFragment mFavoriteFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        registerListeners();
    }

    private void registerListeners() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initComponents() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mActionBar = getSupportActionBar();
        openMusicScreen();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_music:
                openMusicScreen();
                return true;
            case R.id.nav_favorites:
                openFavoriteScreen();
                return true;
            case R.id.nav_settings:
                openSettingScreen();
                return true;
            default:
                return false;
        }

    }

    private void openSettingScreen() {
        mActionBar.setTitle(R.string.title_setting);
        mSettingFragment = new SettingFragment();
        addFragment(mSettingFragment);
    }

    private void openFavoriteScreen() {
        mActionBar.setTitle(R.string.title_favorite);
        mFavoriteFragment = new FavoriteFragment();
        addFragment(mFavoriteFragment);
    }

    private void openMusicScreen() {
        mActionBar.setTitle(R.string.title_music);
        mMusicFragment = new MusicFragment();
        addFragment(mMusicFragment);
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
