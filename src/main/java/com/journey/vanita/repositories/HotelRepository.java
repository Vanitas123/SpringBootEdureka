package com.journey.vanita.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.journey.vanita.model.Hotel;

public interface HotelRepository extends JpaRepository <Hotel, Integer> {

}
