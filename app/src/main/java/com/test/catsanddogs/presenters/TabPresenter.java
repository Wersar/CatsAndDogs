package com.test.catsanddogs.presenters;


import com.test.catsanddogs.contracts.TabContract;
import com.test.catsanddogs.model.Pet;

public class TabPresenter implements TabContract.Presenter {

    private TabContract.View view;
    private Pet[] model;
    private Pet currentPet;

    public TabPresenter(TabContract.View view, Pet[] model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void viewIsReady() {
        getView().removeTabs();
        for(Pet pet: model) {
            getView().addTab();
        }
        getView().selectTab(0);
    }

    @Override
    public void onSelectTab(int tab) {
        Pet pet = Pet.values()[tab];
        switch (pet) {
            case cat:
                getView().switchScreen(pet.name());
                currentPet = pet;
                break;
            case dog:
                getView().switchScreen(pet.name());
                currentPet = pet;
                break;
        }
    }

    @Override
    public Pet getCurrentPet() {
        return currentPet;
    }

    @Override
    public void setCurrentPet(Pet currentPet) {
        this.currentPet = currentPet;
        getView().selectTab(currentPet.ordinal());
    }

    private TabContract.View getView() {
        return view;
    }

//    private Pet getModel() {
//        return model;
//    }

}
