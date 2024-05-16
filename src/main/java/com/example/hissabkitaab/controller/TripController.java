package com.example.hissabkitaab.controller;

import com.example.hissabkitaab.payload.TripDto;
import com.example.hissabkitaab.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/trips")
public class TripController {
    TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    @PostMapping("/createTrip")
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto)
    {
        return new ResponseEntity<>(tripService.createTrip(tripDto), HttpStatus.CREATED);
    }
    @GetMapping("/allTrips")
    public List<TripDto> getTrips()
    {

        return tripService.getAllTrips();
    }
    @GetMapping("/trip/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable(name = "id") long id)
    {
        return ResponseEntity.ok(tripService.getTripById(id));
    }
    @PutMapping("/trip/{id}")
    public ResponseEntity<TripDto> updatePost(@RequestBody TripDto tripDto,@PathVariable(name = "id") long id)
    {
        return new ResponseEntity<>(tripService.updateTrip(tripDto,id),HttpStatus.OK);
    }
}
