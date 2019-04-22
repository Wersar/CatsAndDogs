package com.test.catsanddogs.contracts;


import com.test.catsanddogs.model.Pet;

public interface TabContract {

    interface View {
        void selectTab(int pos);
        void addTab();
        void removeTabs();
        void switchScreen(String key);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void viewIsReady();
        void onSelectTab(int tab);
        Pet getCurrentPet();
        void setCurrentPet(Pet pet);
    }

}
