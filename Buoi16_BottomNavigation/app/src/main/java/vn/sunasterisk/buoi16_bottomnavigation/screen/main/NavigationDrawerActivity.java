package vn.sunasterisk.buoi16_bottomnavigation.screen.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import vn.sunasterisk.buoi16_bottomnavigation.R;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.FavoriteFragment;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.MusicFragment;
import vn.sunasterisk.buoi16_bottomnavigation.screen.music.SettingFragment;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    private MusicFragment mMusicFragment;
    private FavoriteFragment mFavoriteFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        initComponents();
        registerListeners();
    }

    private void registerListeners() {

    }

    private void initComponents() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        openMusicScreen();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_music:
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
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
        mToolbar.setTitle(R.string.title_setting);
        mSettingFragment = new SettingFragment();
        addFragment(mSettingFragment);
        mDrawerLayout.closeDrawers();
    }

    private void openFavoriteScreen() {
        mToolbar.setTitle(R.string.title_favorite);
        mFavoriteFragment = new FavoriteFragment();
        addFragment(mFavoriteFragment);
        mDrawerLayout.closeDrawers();
    }

    private void openMusicScreen() {
        mToolbar.setTitle(R.string.title_music);
        mMusicFragment = new MusicFragment();
        addFragment(mMusicFragment);
        mDrawerLayout.closeDrawers();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
