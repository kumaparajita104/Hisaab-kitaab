package com.example.hissabkitaab.services.Impl;

import com.example.hissabkitaab.entity.Expenses;
import com.example.hissabkitaab.entity.Group;
import com.example.hissabkitaab.entity.Trip;
import com.example.hissabkitaab.exception.ResourceNotFoundException;
import com.example.hissabkitaab.payload.GroupDto;
import com.example.hissabkitaab.payload.TripDto;
import com.example.hissabkitaab.repositories.TripRepository;
import com.example.hissabkitaab.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    TripRepository tripRepository;
    ModelMapper mapper;

    public TripServiceImpl(TripRepository tripRepository, ModelMapper mapper) {
        this.tripRepository = tripRepository;
        this.mapper = mapper;
    }

    @Override
    public TripDto createTrip(TripDto tripDto) {
        Trip trip = mapToEntity(tripDto);

        Trip newTrip = tripRepository.save(trip);

        TripDto newTripDto=maptoDto(newTrip);
        return newTripDto;
    }

    @Override
    public TripDto getTripById(long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip","id",id));

        return maptoDto(trip);
    }

    @Override
    public List<TripDto> getAllTrips() {
        List<Trip> trips=tripRepository.findAll();
        return trips.stream().map(trip -> maptoDto(trip)).collect(Collectors.toList());
    }

    @Override
    public TripDto updateTrip(TripDto tripDto, long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip","id",id));
        trip.setName(tripDto.getName());
        trip.setDays(tripDto.getDays());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setF(tripDto.getF());
        Trip updatedTrip = tripRepository.save(trip);
        return maptoDto(updatedTrip);
    }

    @Override
    public void deleteTripById(long id) {

    }
    private TripDto maptoDto(Trip trip)
    {
        TripDto tripDto=mapper.map(trip, TripDto.class);
        /*CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedAt(LocalDateTime.now());*/
        return tripDto;
    }

    private Trip mapToEntity(TripDto tripDto)
    {
        Trip trip = mapper.map(tripDto,Trip.class);
        /*Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(LocalDateTime.now());*/
        return trip;
    }
}
