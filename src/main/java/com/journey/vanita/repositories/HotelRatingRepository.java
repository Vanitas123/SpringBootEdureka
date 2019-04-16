/**
 * 
 */
package com.journey.vanita.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.journey.vanita.model.HotelRating;

/**
 * @author ADMIN
 *
 */
public interface HotelRatingRepository extends JpaRepository<HotelRating, Integer> {

	Page<HotelRating> findByHotelId(int id, Pageable pageable);
}
