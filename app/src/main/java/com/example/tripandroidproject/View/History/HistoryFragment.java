package com.example.tripandroidproject.View.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripandroidproject.POJOs.Trip;
import com.example.tripandroidproject.R;
import com.example.tripandroidproject.View.NavDrawer_UpComingTrip.TripAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements HistoryAdapter.CancelledTrip {

    private RecyclerView.Adapter myAdapter;
    private RecyclerView recyclerView;
    private List<Trip> values;
    private Trip trip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.historyfragment, container, false);
        recyclerView = view.findViewById(R.id.recycleViewHistory);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        values = new ArrayList<>();
        myAdapter = new HistoryAdapter(getContext(),values,this);
        myAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setCanceledTrip(Trip trip){
        values.add(trip);
    }

    @Override
    public void getPosition(int position) {
        trip = values.get(position);
        setCanceledTrip(trip);
    }
}
//    interface CommunicatorFrag {
//        public void cancelTrip(Trip trip);
//    }
