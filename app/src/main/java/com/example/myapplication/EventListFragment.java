package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventListFragment extends Fragment {

    private RecyclerView recyclerView;
    // Asumimos que ya tienes un adaptador llamado EventAdapter
    private EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        recyclerView = view.findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Aquí deberías inicializar tu adaptador con los datos de los eventos
        // adapter = new EventAdapter(/* tus eventos aquí */);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
