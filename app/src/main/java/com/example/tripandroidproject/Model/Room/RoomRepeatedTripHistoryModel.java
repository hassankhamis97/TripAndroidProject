package com.example.tripandroidproject.Model.Room;

import android.content.Context;

import androidx.room.Room;

import com.example.tripandroidproject.Contract.Trip.ITripPresenter;
import com.example.tripandroidproject.POJOs.RepeatedTripHistory;

public class RoomRepeatedTripHistoryModel {
    private final AppDatabase database;
    private final RepeatedTripHistoryDAO repeatedTripHistoryDAO;
    private ITripPresenter tripPresenter;

    public RoomRepeatedTripHistoryModel(ITripPresenter tripPresenter, Context context) {
        this.tripPresenter = tripPresenter;
        database = Room.databaseBuilder(context, AppDatabase.class, "db-trips")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        repeatedTripHistoryDAO = database.getRepeatedTripHistoryDAO();
    }
}
