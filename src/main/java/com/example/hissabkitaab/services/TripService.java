package com.example.hissabkitaab.services;

import com.example.hissabkitaab.payload.TripDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripDto tripDto);


    TripDto getTripById(long id);
    List<TripDto> getAllTrips();
    TripDto updateTrip(TripDto tripDto,long id);

    void deleteTripById(long id);
}
