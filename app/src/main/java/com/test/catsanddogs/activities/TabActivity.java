package com.test.catsanddogs.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.test.catsanddogs.R;
import com.test.catsanddogs.contracts.TabContract;
import com.test.catsanddogs.fragments.PetFragment;
import com.test.catsanddogs.model.Pet;
import com.test.catsanddogs.presenters.TabPresenter;


public class TabActivity extends AppCompatActivity implements TabContract.View {
    private static final String TAB_CURRENT = "current_tab";

    private View mainView;
    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private TabContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainView = (View) findViewById(R.id.mainView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayoutListener());

        presenter = new TabPresenter(this, Pet.values());
        presenter.viewIsReady();

        restoreState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAB_CURRENT, presenter.getCurrentPet());

    }

    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Pet pet = (Pet) savedInstanceState.get(TAB_CURRENT);
            presenter.setCurrentPet(pet);
        }
    }

    @Override
    public void selectTab(final int pos) {
        TabLayout.Tab tab = tabLayout.getTabAt(pos);
        tab.select();
    }

    @Override
    public void addTab() {
        String tabName = "tab";
        int tabOrder = tabLayout.getTabCount() + 1;
        tabLayout.addTab(tabLayout.newTab().setText(tabName + " " + tabOrder));
    }

    @Override
    public void removeTabs() {
        tabLayout.removeAllTabs();
    }

    @Override
    public void switchScreen(String pet) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        for (Fragment frag: fm.getFragments()) {
            transaction.hide(frag);
        }

        Fragment fragment = fm.findFragmentByTag(pet);
        if (fragment == null) {
            fragment = PetFragment.newInstance(pet);
            transaction.add(R.id.fragment_container, fragment, pet);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();

    }

    @Override
    public void showProgress() {
        mainView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        mainView.setVisibility(View.VISIBLE);
    }

    private class TabLayoutListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            presenter.onSelectTab(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }
}
