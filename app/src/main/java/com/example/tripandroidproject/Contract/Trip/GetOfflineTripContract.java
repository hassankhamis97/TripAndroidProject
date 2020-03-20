package com.example.tripandroidproject.Contract.Trip;

import com.example.tripandroidproject.Contract.Base.IBase;
import com.example.tripandroidproject.POJOs.Note;
import com.example.tripandroidproject.POJOs.Trip;

import java.util.List;

public class GetOfflineTripContract {
    public interface IGetOfflineTripPresenter extends IBase,ITripPresenter {
        public void getOfflineTrip();
        public void getOfflineNoteWithSpecificTrip(String tripID);
    }
    public interface IGetOfflineTripModel {
        public List<Trip> getOfflineTrip();
//        public List<Note> getOfflineNote(String tripID);
    }
}