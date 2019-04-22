package com.test.catsanddogs.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.catsanddogs.App;
import com.test.catsanddogs.model.DataHolder;
import com.test.catsanddogs.PetCache;
import com.test.catsanddogs.model.PetDataSourceFactory;
import com.test.catsanddogs.model.PetRepository;
import com.test.catsanddogs.activities.DetailActivity;
import com.test.catsanddogs.R;
import com.test.catsanddogs.adapters.PetsAdapter;
import com.test.catsanddogs.contracts.PetContract;
import com.test.catsanddogs.data.Pet;
import com.test.catsanddogs.presenters.PetPresenter;

import java.util.List;

public class PetFragment extends Fragment implements PetContract.View, PetsAdapter.OnItemClickListener {
    private static final String PET = "pet";

    private PetContract.Presenter mPresenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public PetFragment() {
    }

    public static PetFragment newInstance(String pet) {
        Bundle args = new Bundle();
        args.putString(PET, pet);
        PetFragment fragment = new PetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataHolder dataHolder = null;
        if (getArguments() != null) {
            String pet = getArguments().getString(PET);

            PetCache cache = ((App) getActivity().getApplication()).getCache();
            PetDataSourceFactory factory = new PetDataSourceFactory(cache);
            dataHolder = new PetRepository(factory).getPets(pet);
        }
        mPresenter = new PetPresenter(dataHolder);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pets, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPresenter.onAttach(this);

        return view;
    }

    @Override
    public void setDataOnAdapter(final List<Pet> data) {
        final PetsAdapter.OnItemClickListener listener = this;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PetsAdapter adapter = new PetsAdapter(getActivity(), data);
                adapter.setOnItemClickListener(listener);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDetail(Pet pet) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.PET_EXTRA, pet);
        startActivity(intent);
    }

    @Override
    public void hideProgress() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void showProgress() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.onItemClick(position);
    }
}
