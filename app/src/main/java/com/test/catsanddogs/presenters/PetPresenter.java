package com.test.catsanddogs.presenters;

import com.test.catsanddogs.model.DataHolder;
import com.test.catsanddogs.contracts.PetContract;
import com.test.catsanddogs.data.Pet;

import java.util.List;

public class PetPresenter implements PetContract.Presenter {

    private PetContract.View view;
    private DataHolder model;

    public PetPresenter(DataHolder model) {
        this.model = model;
    }

    @Override
    public void onAttach(PetContract.View view) {
        this.view = view;
        updateUI();
    }

    @Override
    public void onItemClick(final int position) {
        if (modelIsAvailable()) {
            getModel().observer(new DataHolder.Observer() {
                @Override
                public void onChange(List<Pet> data) {
                    Pet pet = data.get(position);
                    getView().onDetail(pet);
                }
            });
        }
    }

    private PetContract.View getView() {
        return view;
    }

    private DataHolder getModel() {
        return model;
    }

    private boolean modelIsAvailable() {
        return getModel() != null;
    }

    private void updateUI() {
        getView().showProgress();
        if (modelIsAvailable()) {
            getModel().observer(new DataHolder.Observer() {
                @Override
                public void onChange(List<Pet> data) {
                    getView().setDataOnAdapter(data);
                    getView().hideProgress();
                }
            });
        } else {
            getView().hideProgress();
        }
    }
}
