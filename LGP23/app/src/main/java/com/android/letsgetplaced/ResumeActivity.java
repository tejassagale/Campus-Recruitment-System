package com.android.letsgetplaced;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.android.letsgetplaced.datamodel.Resume;
import com.android.letsgetplaced.fragments.EducationFragment;
import com.android.letsgetplaced.fragments.EssentialsFragment;
import com.android.letsgetplaced.fragments.ExperienceFragment;
import com.android.letsgetplaced.fragments.PersonalInfoFragment;
import com.android.letsgetplaced.fragments.PreviewFragment;
import com.android.letsgetplaced.fragments.ProjectsFragment;
import com.android.letsgetplaced.helper.ResumeFragment;

import java.util.Objects;

public class ResumeActivity extends AppCompatActivity {
    private Resume resume;
    NavigationView navigationView;
    private String currentTitle;
    private String STATE_CURRENT_TITLE = "current title";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        setupLayout();

        Gson gson = new Gson();
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String json = mPrefs.getString("SerializableObject", "");
        assert json != null;
        if (json.isEmpty())
            resume = Resume.createNewResume();
        else
            resume = gson.fromJson(json, Resume.class);

        if (savedInstanceState == null) {
            openFragment(PersonalInfoFragment.newInstance(resume));
            currentTitle = getString(R.string.navigation_personal_info);
        } else
            currentTitle = savedInstanceState.getString(STATE_CURRENT_TITLE);

        Objects.requireNonNull(getSupportActionBar()).setTitle(currentTitle);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resume);
        prefsEditor.putString("SerializableObject", json);
        prefsEditor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_CURRENT_TITLE, currentTitle);
    }

    private void setupLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        drawerLayout.closeDrawers();
                        currentTitle = item.getTitle().toString();
                        Objects.requireNonNull(getSupportActionBar()).setTitle(currentTitle);
                        return handleMenuItem(item);
                    }
                });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.content_description_open_drawer,
                R.string.content_description_close_drawer) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDrawerOpened(View drawerView) {
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.steps));
                super.onDrawerOpened(drawerView);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDrawerClosed(View drawerView) {
                Objects.requireNonNull(getSupportActionBar()).setTitle(currentTitle);
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private boolean handleMenuItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_personal_info:
                openFragment(PersonalInfoFragment.newInstance(resume));
                break;
            case R.id.action_essentials:
                openFragment(EssentialsFragment.newInstance(resume));
                break;
            case R.id.action_projects:
                openFragment(ProjectsFragment.newInstance(resume));
                break;
            case R.id.action_education:
                openFragment(EducationFragment.newInstance(resume));
                break;
            case R.id.action_experience:
                openFragment(ExperienceFragment.newInstance(resume));
                break;
            case R.id.action_preview:
                openFragment(PreviewFragment.newInstance(resume));
                break;
            default:
                return false;
        }
        return true;
    }

    private void openFragment(ResumeFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
