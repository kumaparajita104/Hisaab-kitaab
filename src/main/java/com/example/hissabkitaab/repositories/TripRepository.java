package com.example.hissabkitaab.repositories;

import com.example.hissabkitaab.entity.Trip;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
