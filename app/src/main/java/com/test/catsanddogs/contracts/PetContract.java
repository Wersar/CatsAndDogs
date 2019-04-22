package com.test.catsanddogs.contracts;

import com.test.catsanddogs.data.Pet;

import java.util.List;

public interface PetContract {

    interface View {
        void setDataOnAdapter(List<Pet> data);
        void onDetail(Pet pet);
        void hideProgress();
        void showProgress();
    }

    interface Presenter {
        void onAttach(View view);
        void onItemClick(int position);
    }

}
